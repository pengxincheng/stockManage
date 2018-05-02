package com.business.stock.dao.impl;

import com.business.stock.dao.StockLogDao;
import com.business.stock.po.Stock;
import com.business.stock.po.StockLog;
import com.order.cc.sys.dao.FoHQLQuery;
import com.sysBasic.dao.impl.BasicDaoImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by pxc on 2018/4/30.
 */
@Repository
public class StockLogDaoImpl extends BasicDaoImpl<StockLog> implements StockLogDao {

    @Override
    public List<StockLog> getAll(StockLog stockLog) {
        FoHQLQuery query = new FoHQLQuery();
        String hql = " from StockLog sl " +
                " left join fetch sl.product p" +
                " left join fetch sl.warehouse w " +
                " left join fetch sl.user u where 1=1 ";
        hql += this.getConditions(query, stockLog);

        hql += " order by sl.createTime desc ";
        query.setHQL(hql);
        return execFoQuery(query);
    }

    private String getConditions(FoHQLQuery query, StockLog stockLog) {
        String conditions = "";
        if (StringUtils.isNotEmpty(stockLog.getStockId())) {
            conditions += " and sl.stockId =:stockId ";
            query.setString("stockId", stockLog.getStockId());
        }
        if (StringUtils.isNotEmpty(stockLog.getLogType())) {
            conditions += " and sl.logType =:type ";
            query.setString("type", stockLog.getLogType());
        }
        return conditions;
    }
}
