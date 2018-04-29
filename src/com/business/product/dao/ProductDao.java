package com.business.product.dao;

import com.business.product.po.Product;
import com.sysBasic.dao.BasicDao;

import java.util.List;

/**
 * Created by pxc on 2018/4/29.
 */
public interface ProductDao extends BasicDao<Product>{

    List<Product> getAll(Product product);
}

