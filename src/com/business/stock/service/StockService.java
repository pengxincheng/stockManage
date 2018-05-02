package com.business.stock.service;

import com.business.stock.po.Stock;

import java.util.List;

/**
 * Created by pxc on 2018/4/30.
 */
public interface StockService {

    /**
     * 出入库看商品是否在库存单中
     * @param productId
     * @param werehouseId
     * @return
     */
    Stock getByPIdFromWarehouse(String productId,String werehouseId);

    /**
     * 判断库存数量是否告警
     * @param stock
     * @return
     */
    Stock isWaring(Stock stock);

    /**
     * 列表
     * @param stock
     * @return
     */
    List<Stock> getAll(Stock stock);

}
