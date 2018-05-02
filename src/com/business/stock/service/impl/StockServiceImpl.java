package com.business.stock.service.impl;

import com.business.stock.dao.StockDao;
import com.business.stock.po.Stock;
import com.business.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pxc on 2018/4/30.
 */
@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockDao stockDao;

    @Override
    public Stock getByPIdFromWarehouse(String productId, String werehouseId) {
        return stockDao.getByPIdFromWarehouse(productId, werehouseId);
    }

    @Override
    public Stock isWaring(Stock stock) {
        if (stock.getCount() < 10) {
            stock.setIsWaring("T");
        } else {
            stock.setIsWaring("F");
        }
        return stock;
    }

    @Override
    public List<Stock> getAll(Stock stock) {
        return null;
    }
}
