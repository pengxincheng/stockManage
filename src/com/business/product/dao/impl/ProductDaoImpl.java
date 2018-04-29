package com.business.product.dao.impl;

import com.business.product.dao.ProductDao;
import com.business.product.po.Product;
import com.order.cc.sys.dao.FoHQLQuery;
import com.sysBasic.dao.impl.BasicDaoImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by pxc on 2018/4/30.
 */
@Repository
public class ProductDaoImpl extends BasicDaoImpl<Product> implements ProductDao{

    @Override
    public List<Product> getAll(Product product) {
        FoHQLQuery query = new FoHQLQuery();
        String hql = " from Product p left join fetch p.type where 1=1 ";

        if(StringUtils.isNotEmpty(product.getProductName())){
            hql += " and p.productName like :productName ";
            query.setString("productName",product.getProductName());
        }
        if(StringUtils.isNotEmpty(product.getProductType())){
            hql += " and p.productType like :productType ";
            query.setString("productType",product.getProductType());
        }
        if(StringUtils.isNotEmpty(product.getIsDelete())){
            hql += " and p.isDelete like :isDelete ";
            query.setString("isDelete",product.getIsDelete());
        }
        query.setHQL(hql);
        return this.execFoQuery(query);
    }

}
