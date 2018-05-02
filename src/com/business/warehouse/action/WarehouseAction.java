package com.business.warehouse.action;

import com.business.role.po.Role;
import com.business.user.action.UserAction;
import com.business.user.po.User;
import com.business.warehouse.po.Warehouse;
import com.business.warehouse.service.WarehouseService;
import com.sysBasic.action.BasicAction;
import com.utils.JSONUtils;
import com.utils.Response;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * Created by pxc on 2018/4/27.
 */
@Namespace("/warehouse")
@ParentPackage("json-default")
public class WarehouseAction extends BasicAction {
    private final Logger logger = LoggerFactory.getLogger(UserAction.class);
    @Autowired
    private WarehouseService warehouseService;

    @Action(value = "list", results = {@Result(name = "success", type = "json", params = {"root", "response"})})
    public String getAllUser() {
        try {
            if (null == warehouse) {
                warehouse = new Warehouse();
            }
            List<Warehouse> warehouses = warehouseService.getAllWarehouse(warehouse);
            response = Response.ok(JSONUtils.toJSON(warehouses,"role"));
        } catch (Exception e) {
            response = Response.error();
            logger.error(e.getMessage(), e);
        }
        return SUCCESS;
    }


    @Action(value = "add", results = {@Result(name = "success", type = "json", params = {"root", "response"})})
    public String addWarehouse() {
        try {
            //id不为空  修改
            if (StringUtils.isNotBlank(warehouse.getId())) {
                warehouseService.update(warehouse);
            } else {
                warehouseService.saveWarehouse(warehouse);
            }

            response = Response.ok();
        } catch (Exception e) {
            response = Response.error();
            logger.error(e.getMessage(), e);
        }
        return SUCCESS;
    }

    @Action(value = "getById", results = {@Result(name = "success", type = "json", params = {"root", "response"})})
    public String getById() {
        try {
            if (null == id) {
                response = Response.error();
            }
            Warehouse warehouse = warehouseService.getById(id);
            response = Response.ok(JSONUtils.toJSON(warehouse));
        } catch (Exception e) {
            response = Response.error();
            logger.error(e.getMessage(), e);
        }
        return SUCCESS;
    }

    @Action(value = "del", results = {@Result(name = "success", type = "json", params = {"root", "response"})})
    public String delWarehouse() {
        try {
            if (null == id) {
                response = Response.error();
            }
            warehouseService.delWarehouse(id);
            response = Response.ok();
        } catch (Exception e) {
            response = Response.error();
            logger.error(e.getMessage(), e);
        }
        return SUCCESS;
    }

    private Warehouse warehouse;
    private String id;

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
