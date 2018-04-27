package com.business.warehouse.dao.impl;

import com.business.warehouse.po.Warehouse;
import com.business.warehouse.dao.WarehouseDao;
import com.order.cc.sys.dao.FoHQLQuery;
import com.sysBasic.dao.impl.BasicDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by pxc on 2018/4/26.
 */
@Repository
public class WarehouseDaoImpl extends BasicDaoImpl<Warehouse> implements WarehouseDao {
    @Override
    public List<Warehouse> getAllWarehouse(Warehouse warehouse) {
        FoHQLQuery query = new FoHQLQuery();
        String hql = " from Warehouse w where 1=1 ";
        query.setHQL(hql);
        return execFoQuery(query);
    }
}
