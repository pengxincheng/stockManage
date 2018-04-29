package com.business.product.service.impl;

import com.business.product.dao.ProductTypeDao;
import com.business.product.po.ProductType;
import com.business.product.service.ProductTypeService;
import com.business.user.po.User;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by pxc on 2018/4/29.
 */
@Service
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    private ProductTypeDao productTypeDao;

    @Override
    public void save(ProductType productType) {
        User currentUser = (User) ServletActionContext.getRequest().getSession().getAttribute("currentUser");
        productType.setCreateTime(new Date());
        productType.setCreateUserId(currentUser.getUserId());
        productType.setIsDelete("F");
        productTypeDao.saveEntity(productType);
    }

    @Override
    public void del(String id) {
        ProductType entity = productTypeDao.getEntityById(id);
        User currentUser = (User) ServletActionContext.getRequest().getSession().getAttribute("currentUser");
        entity.setUpdateUserId(currentUser.getUserId());
        entity.setUpdateTime(new Date());
        entity.setIsDelete("T");
        productTypeDao.updateEntity(entity);
    }

    @Override
    public void update(ProductType productType) {
        User currentUser = (User) ServletActionContext.getRequest().getSession().getAttribute("currentUser");
        ProductType entity = productTypeDao.getEntityById(productType.getTypeId());
        entity.setTypeName(productType.getTypeName());
        entity.setRemark(productType.getRemark());
        entity.setUpdateUserId(currentUser.getUserId());
        entity.setUpdateTime(new Date());
        productTypeDao.saveEntity(entity);
    }

    @Override
    public List<ProductType> getAll(ProductType productType) {
        return productTypeDao.getAll(productType);
    }

    @Override
    public ProductType getById(String id) {
        return productTypeDao.getEntityById(id);
    }
}
