package com.business.product.action;

import com.business.product.po.Product;
import com.business.product.service.ProductService;
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
@Namespace("/product")
@ParentPackage("json-default")
public class ProductAction extends BasicAction {

    private final Logger logger = LoggerFactory.getLogger(ProductAction.class);

    @Autowired
    private ProductService productService;


    @Action(value = "list", results = {@Result(name = "success", type = "json", params = {"root", "response"})})
    public String getAll() {
        try {
            if (null == product) {
                product = new Product();
            }
            product.setIsDelete("F");
            List<Product> products = productService.getAll(product);
            response = Response.ok(JSONUtils.toJSON(products,"role"));
        } catch (Exception e) {
            response = Response.error();
            logger.error(e.getMessage(), e);
        }
        return SUCCESS;
    }

    @Action(value = "getById", results = {@Result(name = "success", type = "json", params = {"root", "response"})})
    public String getById() {
        try {
            Product entity = productService.getById(id);
            response = Response.ok(JSONUtils.toJSON(entity,"type","pCreateUser"));
        } catch (Exception e) {
            response = Response.error();
            logger.error(e.getMessage(), e);
        }
        return SUCCESS;
    }

    @Action(value = "addOrUpdate", results = {@Result(name = "success", type = "json", params = {"root", "response"})})
    public String addOrUpdate() {
        try {
            if (null == product) {
                response = Response.error();
            }
            if (StringUtils.isNotEmpty(product.getProductId())) {
                productService.update(product);
            } else {
                productService.save(product);
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
            productService.del(id);
            response = Response.ok();
        } catch (Exception e) {
            response = Response.error();
            logger.error(e.getMessage(), e);
        }
        return SUCCESS;
    }

    private Product product;
    private String id;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
