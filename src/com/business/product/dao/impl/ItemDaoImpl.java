package com.business.product.dao.impl;

import com.business.product.dao.ItemDao;
import com.business.product.po.Item;
import com.order.cc.sys.dao.FoHQLQuery;
import com.sysBasic.dao.impl.BasicDaoImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by pxc on 2018/4/30.
 */
@Repository
public class ItemDaoImpl extends BasicDaoImpl<Item> implements ItemDao {

    @Override
    public List<Item> getAll(Item item) {
        FoHQLQuery query = new FoHQLQuery();
        String hql = " from Item i left join fetch i.product " +
                "left join fetch i.product.type " +
                "left join fetch i.warehouse " +
                "left join fetch i.supplier " +
                "left join fetch i.inUser where 1=1 ";
        hql += this.getCondition(query, item);
        query.setHQL(hql);
        return this.execFoQuery(query);
    }

    @Override
    public List<Item> getInStockItem(String productId, String warehouseId) {
        String hql = " from Item i where i.productId = ? and warehouseId=? and itemStatus = '在库' order by i.inTime desc ";
        return this.find(hql,productId,warehouseId);
    }

    private String getCondition(FoHQLQuery query, Item item) {
        String conditions = "";
        if (StringUtils.isNotEmpty(item.getInUserId())) {
            conditions += " and i.inUserId = :inUserId ";
            query.setString("inUserId", item.getInUserId());
        }
        if (StringUtils.isNotEmpty(item.getProductId())) {
            conditions += " and i.productId = :productId ";
            query.setString("productId", item.getProductId());
        }
        if (StringUtils.isNotEmpty(item.getTypeId())) {
            conditions += " and i.typeId = :typeId ";
            query.setString("typeId", item.getTypeId());
        }

        if (StringUtils.isNotEmpty(item.getSupplierId())) {
            conditions += " and i.supplierId = :supplierId ";
            query.setString("supplierId", item.getSupplierId());
        }
        if (StringUtils.isNotEmpty(item.getCustomerId())) {
            conditions += " and i.customerId = :customerId ";
            query.setString("customerId", item.getCustomerId());
        }
        if (StringUtils.isNotEmpty(item.getWarehouseId())) {
            conditions += " and i.warehouseId = :warehouseId ";
            query.setString("warehouseId", item.getWarehouseId());
        }
        if (StringUtils.isNotEmpty(item.getItemStatus())) {
            conditions += " and i.itemStatus = :itemStatus ";
            query.setString("itemStatus", item.getItemStatus());
        }
        if (StringUtils.isNotEmpty(item.getInId())) {
            conditions += " and i.inId = :inId ";
            query.setString("inId", item.getInId());
        }
        if (StringUtils.isNotEmpty(item.getOutId())) {
            conditions += " and i.outId = :outId ";
            query.setString("outId", item.getOutId());
        }
        return conditions;

    }
}
