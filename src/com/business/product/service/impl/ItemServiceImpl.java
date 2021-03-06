package com.business.product.service.impl;

import com.business.product.dao.ItemDao;
import com.business.product.exception.ItemException;
import com.business.product.po.Item;
import com.business.product.service.ItemService;
import com.business.stock.dao.StockDao;
import com.business.stock.dao.StockLogDao;
import com.business.stock.po.Stock;

import com.business.stock.po.StockLog;
import com.business.stock.service.StockService;
import com.business.user.po.User;
import com.utils.DateConvertUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by pxc on 2018/4/30.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private StockDao stockDao;
    @Autowired
    private ItemDao itemDao;
    @Autowired
    private StockService stockService;
    @Autowired
    private StockLogDao stockLogDao;

    private Date date = DateConvertUtils.formatStringToDate("2018-04-01 12:00:00","yyyy-MM-dd HH:mm:ss");

    @Override
    public void inStock(Item item, Integer inCount) {
        //1按照商品id和仓库id找库存 记录  有入库数量加inCount  没有 新增
        User currentUser = (User) ServletActionContext.getRequest().getSession().getAttribute("currentUser");
        Stock stock = stockDao.getByPIdFromWarehouse(item.getProductId(), item.getWarehouseId());
        if (null == stock) {
            stock = new Stock();
            stock.setInCount(stock.getInCount() + inCount);
            stock.setCount(stock.getCount() + inCount);
            stock.setProductId(item.getProductId());
            stockService.isWaring(stock);//是否告警
            stock.setWareHouseId(item.getWarehouseId());
            stockDao.saveEntity(stock);
        } else {
            stock.setInCount(stock.getInCount() + inCount);
            stock.setCount(stock.getCount() + inCount);
            stockService.isWaring(stock);//是否告警
            stockDao.updateEntity(stock);
        }
        //库存记录入库
        StockLog stockLog = new StockLog();
        stockLog.setLogType("入库");
        stockLog.setTotalCount(inCount);
       // stockLog.setCreateTime(new Date());
        stockLog.setCreateTime(date);
        stockLog.setProductId(item.getProductId());
        stockLog.setTotalMoney(item.getInPrice().multiply(new BigDecimal(inCount)));
        stockLog.setRemark("商品入库");
        stockLog.setUserId(currentUser.getUserId());
        stockLog.setStockId(stock.getId());
        stockLog.setWareHouseId(stock.getWareHouseId());
        stockLogDao.saveEntity(stockLog);
        //商品详情入库
       // item.setInTime(new Date());
        item.setInTime(date);
        item.setInUserId(currentUser.getUserId());
        item.setItemStatus("在库");
        item.setInId(stockLog.getId());
        for (int i = 0; i < inCount; i++) {
            itemDao.saveEntity(item);
        }
    }

    @Override
    public void outStock(Item item, Integer outCount) {
        User currentUser = (User) ServletActionContext.getRequest().getSession().getAttribute("currentUser");
        Stock stock = stockDao.getByPIdFromWarehouse(item.getProductId(), item.getWarehouseId());
        if (null == stock) {
            throw new ItemException("所选仓库不存在该商品！");
        }
        if (stock.getCount() < outCount) {
            throw new ItemException("库存商品数量小于出库数量！");
        }
        stock.setOutCount(stock.getOutCount() + outCount);
        stock.setCount(stock.getCount() - outCount);
        stockService.isWaring(stock);//是否告警
        stockDao.updateEntity(stock);
        //商品详情修改
        BigDecimal totalInPrice = new BigDecimal("0");
        List<Item> itemList = itemDao.getInStockItem(item.getProductId(), item.getWarehouseId());
        for (int i = 0; i < outCount; i++) {
            Item item1 = itemList.get(i);
            totalInPrice = totalInPrice.add(item1.getInPrice());
        }

        //库存记录入库
        StockLog stockLog = new StockLog();
        stockLog.setLogType("出库");
        stockLog.setTotalCount(outCount);
        //stockLog.setCreateTime(new Date());
        stockLog.setCreateTime(date);
        stockLog.setProductId(item.getProductId());
        stockLog.setTotalMoney(item.getOutPrice().multiply(new BigDecimal(outCount)));
        stockLog.setRemark("商品出库");
        stockLog.setUserId(currentUser.getUserId());
        stockLog.setStockId(stock.getId());
        stockLog.setWareHouseId(stock.getWareHouseId());
        stockLog.setProfit(stockLog.getTotalMoney().subtract(totalInPrice));
        stockLogDao.saveEntity(stockLog);

        for (int i = 0; i < outCount; i++) {
            Item item1 = itemList.get(i);
            item1.setItemStatus("已出库");
            item1.setOutPrice(item.getOutPrice());
            //item1.setOutTime(new Date());
            item1.setOutTime(date);
            item1.setOutUserId(currentUser.getUserId());
            item1.setCustomerId(item.getCustomerId());
            item1.setOutId(stockLog.getId());
            totalInPrice = totalInPrice.add(item1.getInPrice());
            itemDao.updateEntity(item1);
        }
    }

    @Override
    public Item getById(String id) {
        return null;
    }

    @Override
    public List<Item> getAll(Item item) {
        return itemDao.getAll(item);
    }
}
