package com.business.product.action;

import com.business.product.po.ProductType;
import com.business.product.service.ProductTypeService;
import com.sysBasic.action.BasicAction;
import com.utils.JSONUtils;
import com.utils.Response;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by pxc on 2018/4/29.
 */
@Namespace("/productType")
@ParentPackage("json-default")
public class ProductTypeAction extends BasicAction {

    private final Logger logger = LoggerFactory.getLogger(ProductTypeAction.class);

    @Autowired
    private ProductTypeService productTypeService;


    @Action(value = "list", results = {@Result(name = "success", type = "json", params = {"root", "response"})})
    public String getAllUser() {
        try {
            if (null == productType) {
                productType = new ProductType();
            }
            productType.setIsDelete("F");
            List<ProductType> productTypes = productTypeService.getAll(productType);
            response = Response.ok(JSONUtils.toJSON(productTypes));
        } catch (Exception e) {
            response = Response.error();
            logger.error(e.getMessage(), e);
        }
        return SUCCESS;
    }

    @Action(value = "getById", results = {@Result(name = "success", type = "json", params = {"root", "response"})})
    public String getById() {
        try {
            ProductType entity = productTypeService.getById(id);
            response = Response.ok(JSONUtils.toJSON(entity));
        } catch (Exception e) {
            response = Response.error();
            logger.error(e.getMessage(), e);
        }
        return SUCCESS;
    }

    @Action(value = "addOrUpdate", results = {@Result(name = "success", type = "json", params = {"root", "response"})})
    public String addOrUpdate() {
        try {
            if (null == productType) {
                response = Response.error();
            }
            if (StringUtils.isNotEmpty(productType.getTypeId())) {
                productTypeService.update(productType);
            } else {
                productTypeService.save(productType);
            }
            response = Response.ok();
        } catch (Exception e) {
            response = Response.error();
            logger.error(e.getMessage(), e);
        }
        return SUCCESS;
    }

    @Action(value = "delete", results = {@Result(name = "success", type = "json", params = {"root", "response"})})
    public String delete() {
        try {
           productTypeService.del(id);
            response = Response.ok();
        } catch (Exception e) {
            response = Response.error();
            logger.error(e.getMessage(), e);
        }
        return SUCCESS;
    }

    private ProductType productType;
    private String id;

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
