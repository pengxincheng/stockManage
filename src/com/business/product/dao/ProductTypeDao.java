package com.business.product.dao;

import com.business.product.po.ProductType;
import com.sysBasic.dao.BasicDao;

import java.util.List;

/**
 * Created by pxc on 2018/4/29.
 */
public interface ProductTypeDao extends BasicDao<ProductType>{

    /**
     * 获取类型列表
     * @return
     */
    List<ProductType> getAll(ProductType productType);
}
