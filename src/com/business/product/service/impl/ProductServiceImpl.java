package com.business.product.service.impl;

import com.business.product.dao.ProductDao;
import com.business.product.po.Product;
import com.business.product.service.ProductService;
import com.business.user.po.User;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by pxc on 2018/4/30.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public void save(Product product) {
        User currentUser = (User) ServletActionContext.getRequest().getSession().getAttribute("currentUser");
        product.setCreateUserId(currentUser.getUserId());
        product.setCreateTime(new Date());
        product.setIsDelete("F");
        productDao.saveEntity(product);

    }

    @Override
    public void del(String id) {
        Product p = productDao.getEntityById(id);
        p.setIsDelete("T");
        User currentUser = (User) ServletActionContext.getRequest().getSession().getAttribute("currentUser");
        p.setUpdateUserId(currentUser.getUserId());
        p.setUpdateTime(new Date());
        productDao.updateEntity(p);
    }

    @Override
    public void update(Product product) {
        Product p = productDao.getEntityById(product.getProductId());
        p.setProductName(product.getProductName());
        p.setProductType(product.getProductType());
        p.setRemark(product.getRemark());
        User currentUser = (User) ServletActionContext.getRequest().getSession().getAttribute("currentUser");
        p.setUpdateUserId(currentUser.getUserId());
        p.setUpdateTime(new Date());
        productDao.updateEntity(p);
    }

    @Override
    public List<Product> getAll(Product product) {
        return productDao.getAll(product);
    }

    @Override
    public Product getById(String id) {
        return productDao.getEntityById(id);
    }
}
