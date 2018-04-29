package com.business.product.service;

import com.business.product.po.ProductType;

import java.util.List;

/**
 * Created by pxc on 2018/4/29.
 */
public interface ProductTypeService {

    void save(ProductType productType);

    void del(String id);

    void update(ProductType productType);

    List<ProductType> getAll(ProductType productType);

    ProductType getById(String id);
}
