package com.business.warehouse.service;

import com.business.warehouse.po.Warehouse;

import java.util.List;

/**
 * Created by pxc on 2018/4/27.
 */
public interface WarehouseService {

    /**
     * 列表
     *
     * @param warehouse
     * @return
     */
    List<Warehouse> getAllWarehouse(Warehouse warehouse);

    /**
     * 保存
     *
     * @param warehouse
     */
    void saveWarehouse(Warehouse warehouse);

    /**
     * 删除
     *
     * @param id
     */
    void delWarehouse(String id);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    Warehouse getById(String id);

    void update(Warehouse warehouse);
}
