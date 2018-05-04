package com.business.stock.dao.impl;

import com.business.stock.dao.StockLogDao;
import com.business.stock.po.Stock;
import com.business.stock.po.StockLog;
import com.order.cc.sys.dao.FoHQLQuery;
import com.order.cc.sys.dao.FoSQLQuery;
import com.sysBasic.dao.impl.BasicDaoImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

    @Override
    public List<Map> getStockCountGroupMonth(StockLog stockLog) {
        FoSQLQuery query = new FoSQLQuery();
        String sql = " SELECT " +
                "date_format(sl.create_time, '%Y-%m') AS myMonth, " +
                "SUM(sl.total_count) AS totalCount, " +
                "sl.type AS type, " +
                "sum(sl.profit) as profit " +
                "FROM " +
                "tab_stock_log sl " +
                "GROUP BY " +
                "date_format(sl.create_time, '%Y-%m'), " +
                "sl.type  ";
        query.setSQL(sql);
        query.setEntityMap(true);
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
        if (StringUtils.isNotBlank(stockLog.getProductId())) {
            conditions += " and sl.productId = :productId ";
            query.setString("productId", stockLog.getProductId());
        }
        if (StringUtils.isNotBlank(stockLog.getWareHouseId())) {
            conditions += " and sl.wareHouseId = :wareHouseId ";
            query.setString("wareHouseId", stockLog.getWareHouseId());
        }
        if (StringUtils.isNotBlank(stockLog.getUserId())) {
            conditions += " and sl.userId = :userId ";
            query.setString("userId", stockLog.getUserId());
        }
        return conditions;
    }
}
