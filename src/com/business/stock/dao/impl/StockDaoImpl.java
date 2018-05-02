package com.business.stock.dao.impl;

import com.business.stock.dao.StockDao;
import com.business.stock.po.Stock;
import com.order.cc.sys.dao.FoHQLQuery;
import com.sysBasic.dao.impl.BasicDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by pxc on 2018/4/30.
 */
@Repository
public class StockDaoImpl extends BasicDaoImpl<Stock> implements StockDao{

    @Override
    public Stock getByPIdFromWarehouse(String productId, String warehouseId) {
        String hql = " from Stock s where s.productId = ? and s.wareHouseId = ? ";
        return this.findEntity(hql,productId,warehouseId);
    }

    @Override
    public List<Stock> getAll(Stock stock) {
        FoHQLQuery query = new FoHQLQuery();
        String hql = " from Stock s where 1=1 ";
        hql += this.getConditions(query,stock);
        query.setHQL(hql);
        return execFoQuery(query);
    }


    private String getConditions(FoHQLQuery query,Stock stock){
        String conditions = "";
        return conditions;
    }
}
