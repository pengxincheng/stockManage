package com.business.warehouse.dao;

import com.business.warehouse.po.Warehouse;
import com.sysBasic.dao.BasicDao;

import java.util.List;

/**
 * Created by pxc on 2018/4/26.
 */
public interface WarehouseDao extends BasicDao<Warehouse>{

    /**
     * 列表
     * @param warehouse
     * @return
     */
    List<Warehouse> getAllWarehouse(Warehouse warehouse);
}
