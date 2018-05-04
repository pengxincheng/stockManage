package com.business.stock.service;

import com.business.stock.po.StockLog;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

/**
 * Created by pxc on 2018/5/2.
 */
public interface StockLogService {
    /**
     * 列表
     * @param stockLog
     * @return
     */
    List<StockLog> getAll(StockLog stockLog);

    /**
     * 出库单导出
     * @param stockLog
     * @return
     */
    HSSFWorkbook getOutStockLog(StockLog stockLog);
}
