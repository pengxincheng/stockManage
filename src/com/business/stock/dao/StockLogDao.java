package com.business.stock.dao;

import com.business.stock.po.StockLog;
import com.sysBasic.dao.BasicDao;

import java.util.List;

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
}