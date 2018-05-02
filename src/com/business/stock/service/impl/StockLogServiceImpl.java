package com.business.stock.service.impl;

import com.business.stock.dao.StockLogDao;
import com.business.stock.po.StockLog;
import com.business.stock.service.StockLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pxc on 2018/5/2.
 */
@Service
public class StockLogServiceImpl implements StockLogService{

    @Autowired
    private StockLogDao stockLogDao;

    @Override
    public List<StockLog> getAll(StockLog stockLog) {
        return stockLogDao.getAll(stockLog);
    }
}
