package com.business.warehouse.service.impl;

import com.business.user.po.User;
import com.business.warehouse.dao.WarehouseDao;
import com.business.warehouse.po.Warehouse;
import com.business.warehouse.service.WarehouseService;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        User currentUser = (User) ServletActionContext.getRequest().getSession().getAttribute("currentUser");
        warehouse.setCreateUserId(currentUser.getUserId());
        warehouse.setCreateTime(new Date());
        warehouseDao.saveEntity(warehouse);
    }

    @Override
    public void delWarehouse(String id) {
        //todo  检查仓库中是否有商品
    }

    @Override
    public Warehouse getById(String id) {
        return warehouseDao.getEntityById(id);
    }

    @Override
    public void update(Warehouse warehouse) {
        User currentUser = (User) ServletActionContext.getRequest().getSession().getAttribute("currentUser");
        Warehouse entity = warehouseDao.getEntityById(warehouse.getId());
        BeanUtils.copyProperties(warehouse, entity, "id", "createUserId", "createTime");
        entity.setUpdateUserId(currentUser.getUserId());
        entity.setUpdateTime(new Date());
        warehouseDao.updateEntity(entity);
    }
}
