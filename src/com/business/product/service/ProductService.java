package com.business.product.service;

import com.business.product.po.Product;

import java.util.List;

/**
 * Created by pxc on 2018/4/29.
 */
public interface ProductService {

    void save(Product product);

    void del(String id);

    void update(Product product);

    List<Product> getAll(Product product);

    Product getById(String id);
}
