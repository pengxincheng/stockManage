package com.business.stock.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.business.stock.dao.StockLogDao;
import com.business.stock.po.StockLog;
import com.business.stock.service.StockLogService;
import com.utils.DateConvertUtils;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by pxc on 2018/5/2.
 */
@Service
public class StockLogServiceImpl implements StockLogService {

    @Autowired
    private StockLogDao stockLogDao;

    @Override
    public List<StockLog> getAll(StockLog stockLog) {
        return stockLogDao.getAll(stockLog);
    }

    @Override
    public HSSFWorkbook getOutStockLog(StockLog stockLog) {
        List<StockLog> stockLogs = stockLogDao.getAll(stockLog);

        HSSFWorkbook wb = new HSSFWorkbook();

        HSSFSheet sheet = "出库".equals(stockLog.getLogType()) ? wb.createSheet("出库单") : wb.createSheet("出库单");
        HSSFRow row = sheet.createRow(0);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        HSSFCell cell = row.createCell(0);
        cell.setCellValue("时间");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("商品");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("所在仓库");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("数量");
        cell.setCellStyle(style);

        cell = row.createCell(4);
        cell.setCellValue("总金额");
        cell.setCellStyle(style);

        cell = row.createCell(5);
        cell.setCellValue("负责人");
        cell.setCellStyle(style);

        if ("出库".equals(stockLog.getLogType())) {
            cell = row.createCell(6);
            cell.setCellValue("本单盈利");
            cell.setCellStyle(style);
        }
        int i = 0;
        for (StockLog s : stockLogs) {
            row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(DateConvertUtils.formatDateToString(s.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
            row.createCell(1).setCellValue(s.getProduct().getProductName());
            row.createCell(2).setCellValue(s.getWarehouse().getName());
            row.createCell(3).setCellValue(s.getTotalCount());
            row.createCell(4).setCellValue(s.getTotalMoney().toString());
            row.createCell(5).setCellValue(s.getUser().getUserAlias());
            if ("出库".equals(stockLog.getLogType())) {
                row.createCell(6).setCellValue(s.getProfit().toString());
            }
            i++;
        }
        return wb;
    }

    @Override
    public JSONObject getStockCountGroupMonth(StockLog stockLog) {
        List<Map> mapList = stockLogDao.getStockCountGroupMonth(stockLog);
        JSONObject result = new JSONObject();
        int inCount[] = new int[12];
        int outCount[] = new int[12];
        double profit[] = new double[12];
        mapList.forEach(map -> {
            int index = Integer.parseInt(String.valueOf(map.get("myMonth")).split("-")[1]);
            if ("入库".equals(String.valueOf(map.get("type")))) {
                inCount[index-1] = Integer.parseInt(String.valueOf(map.get("totalCount")));
            } else {
                outCount[index-1] = Integer.parseInt(String.valueOf(map.get("totalCount")));
                profit[index-1] = Double.parseDouble(String.valueOf(map.get("profit")));
            }
        });

        result.put("inCount", inCount);
        result.put("outCount", outCount);
        result.put("profit", profit);
        return result;
    }
}
