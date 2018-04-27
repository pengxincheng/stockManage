package com.business.warehouse.service.impl;

import com.business.warehouse.dao.WarehouseDao;
import com.business.warehouse.po.Warehouse;
import com.business.warehouse.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pxc on 2018/4/27.
 */
@Service
public class WarehousreServiceImpl implements WarehouseService {

    @Autowired
    private WarehouseDao warehouseDao;

    @Override
    public List<Warehouse> getAllWarehouse(Warehouse warehouse) {
        return warehouseDao.getAllWarehouse(warehouse);
    }

    @Override
    public void saveWarehouse(Warehouse warehouse) {
         warehouseDao.saveEntity(warehouse);
    }

    @Override
    public void delWarehouse(String id) {
        //todo  检查仓库中是否有商品
    }
}
