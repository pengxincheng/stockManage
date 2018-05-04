package com.business.stock.action;

import com.business.stock.po.Stock;
import com.business.stock.po.StockLog;
import com.business.stock.service.StockLogService;
import com.business.stock.service.StockService;
import com.sun.deploy.net.HttpResponse;
import com.sysBasic.action.BasicAction;
import com.utils.JSONUtils;
import com.utils.Response;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

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
            if ("in".equals(stockLog.getLogType())) {
                stockLog.setLogType("入库");
            }
            if ("out".equals(stockLog.getLogType())) {
                stockLog.setLogType("出库");
            }
            List<StockLog> stockLogs = stockLogService.getAll(stockLog);
            response = Response.ok(JSONUtils.toJSON(stockLogs, "createUser", "pCreateUser", "role", "type"));
        } catch (Exception e) {
            response = Response.error();
            logger.error(e.getMessage(), e);
        }
        return SUCCESS;
    }

    @Action(value = "exportOutStock", results = {@Result(name = "success", type = "stream")})
    public String exportOutStock() {
        try {
            HSSFWorkbook wb = stockLogService.getOutStockLog(stockLog);
            HttpServletResponse resp = ServletActionContext.getResponse();
            String fileName = "出库".equals(stockLog.getLogType()) ? URLEncoder.encode("出库单", "UTF-8") + ".xls" : URLEncoder.encode("入库单", "UTF-8") + ".xls";
            //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
            resp.setContentType("multipart/form-data");
            //2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)
            resp.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
            OutputStream os = resp.getOutputStream();
            logger.info("#########" + os);
            wb.write(os);
            os.close();
        } catch (Exception e) {
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
