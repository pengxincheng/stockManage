package com.business.stock.action;

import com.business.stock.po.Stock;
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
@Namespace("/stock")
@ParentPackage("json-default")
public class StockAction extends BasicAction {
    private final Logger logger = LoggerFactory.getLogger(StockAction.class);

    @Autowired
    private StockService stockService;

    @Action(value = "list", results = {@Result(name = "success", type = "json", params = {"root", "response"})})
    public String getAllUser() {
        try {
            if (null == stock) {
                stock = new Stock();
            }
            List<Stock> stocks = stockService.getAll(stock);
            response = Response.ok(JSONUtils.toJSON(stocks,"createUser","pCreateUser","type"));
        } catch (Exception e) {
            response = Response.error();
            logger.error(e.getMessage(), e);
        }
        return SUCCESS;
    }

    private Stock stock;

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
