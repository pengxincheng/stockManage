package com.business.product.service;

import com.business.product.po.Item;

import java.util.List;

/**
 * Created by pxc on 2018/4/30.
 */
public interface ItemService {

    /**
     * 入库
     * @param item
     */
    void inStock(Item item,Integer inCount);

    /**
     * 出库
     * @param item
     */
    void outStock(Item item);

    Item getById(String id);

    List<Item> getAll(Item item);
}
