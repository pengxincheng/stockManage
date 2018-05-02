package com.business.stock.dao;

import com.business.stock.po.Stock;
import com.sysBasic.dao.BasicDao;

import java.util.List;

/**
 * Created by pxc on 2018/4/30.
 */
public interface StockDao extends BasicDao<Stock> {

    /**
     * 出入库看商品是否在库存单中
     * @param productId
     * @param werehouseId
     * @return
     */
    Stock getByPIdFromWarehouse(String productId,String werehouseId);

    /**
     * 列表
     * @param stock
     * @return
     */
    List<Stock> getAll(Stock stock);
}
