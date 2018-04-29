package com.business.product.dao.impl;

import com.business.product.dao.ProductTypeDao;
import com.business.product.po.ProductType;
import com.order.cc.sys.dao.FoHQLQuery;
import com.sysBasic.dao.impl.BasicDaoImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by pxc on 2018/4/29.
 */
@Repository
public class ProductDaoImpl extends BasicDaoImpl<ProductType> implements ProductTypeDao {

    @Override
    public List<ProductType> getAll(ProductType productType) {
        FoHQLQuery query = new FoHQLQuery();
        String hql = " from ProductType pt where 1=1 ";

        if (StringUtils.isNotEmpty(productType.getTypeName())) {
            hql += " and pt.typeName like :type ";
            query.setString("tyName", "%" + productType.getTypeName() + "%");
        }
        if (StringUtils.isNotEmpty(productType.getIsDelete())) {
            hql += " and pt.isDelete = :isDelete ";
            query.setString("isDelete", productType.getIsDelete());
        }
        query.setHQL(hql);
        return this.execFoQuery(query);
    }
}
