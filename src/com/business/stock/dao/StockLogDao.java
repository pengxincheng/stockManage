package com.business.stock.dao;

import com.business.stock.po.StockLog;
import com.sysBasic.dao.BasicDao;

import java.util.List;
import java.util.Map;

/**
 * Created by pxc on 2018/4/30.
 */
public interface StockLogDao extends BasicDao<StockLog> {

    /**
     * 列表
     * @param stockLog
     * @return
     */
    List<StockLog> getAll(StockLog stockLog);

    /**
     * 出库按月份统计
     * @param stockLog
     * @return
     */
    List<Map> getStockCountGroupMonth(StockLog stockLog);
}
