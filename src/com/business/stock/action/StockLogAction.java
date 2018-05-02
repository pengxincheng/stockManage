package com.business.stock.action;

import com.business.stock.po.Stock;
import com.business.stock.po.StockLog;
import com.business.stock.service.StockLogService;
import com.business.stock.service.StockService;
import com.sysBasic.action.BasicAction;
import com.utils.JSONUtils;
import com.utils.Response;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by pxc on 2018/5/2.
 */
@Namespace("/stockLog")
@ParentPackage("json-default")
public class StockLogAction extends BasicAction {

    private final Logger logger = LoggerFactory.getLogger(StockAction.class);

    @Autowired
    private StockLogService stockLogService;

    @Action(value = "list", results = {@Result(name = "success", type = "json", params = {"root", "response"})})
    public String getAllUser() {
        try {
            if (null == stockLog) {
                stockLog = new StockLog();
            }
            if("in".equals(stockLog.getLogType())){
                stockLog.setLogType("入库");
            }
            if("out".equals(stockLog.getLogType())){
                stockLog.setLogType("出库");
            }
           List<StockLog> stockLogs  = stockLogService.getAll(stockLog);
            response = Response.ok(JSONUtils.toJSON(stockLogs,"createUser","pCreateUser","role","type"));
        } catch (Exception e) {
            response = Response.error();
            logger.error(e.getMessage(), e);
        }
        return SUCCESS;
    }

    private StockLog stockLog;

    public StockLog getStockLog() {
        return stockLog;
    }

    public void setStockLog(StockLog stockLog) {
        this.stockLog = stockLog;
    }
}
