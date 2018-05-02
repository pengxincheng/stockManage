package com.business.product.dao;

import com.business.product.po.Item;
import com.sysBasic.dao.BasicDao;

import java.util.List;

/**
 * Created by pxc on 2018/4/29.
 */
public interface ItemDao extends BasicDao<Item>{

    List<Item> getAll(Item item);
}

