package com.business.stock.po;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

/**
 * Created by pxc on 2018/4/25.
 * 库存
 */
@Entity
@Table(name = "tab_stock")
public class Stock {

    private String id;
    private String productId;
    private String wareHouseId;
    private Integer inCount;   //总入库量
    private Integer outCount;  //总出库量
    private Integer count;     //库存量
    private String isWaring;

    @Id
    @Column(name = "stock_id")
    @GeneratedValue(generator = "StockGenerator")
    @GenericGenerator(name = "StockGenerator", strategy = "uuid")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "product_id")
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Column(name = "warehouse_id")
    public String getWareHouseId() {
        return wareHouseId;
    }

    public void setWareHouseId(String wareHouseId) {
        this.wareHouseId = wareHouseId;
    }

    @Column(name = "in_count")
    public Integer getInCount() {
        return inCount;
    }

    public void setInCount(Integer inCount) {
        this.inCount = inCount;
    }

    @Column(name = "out_count")
    public Integer getOutCount() {
        return outCount;
    }

    public void setOutCount(Integer outCount) {
        this.outCount = outCount;
    }

    @Column(name = "is_waring")
    public String getIsWaring() {
        return isWaring;
    }

    public void setIsWaring(String isWaring) {
        this.isWaring = isWaring;
    }

    @Column(name = "count")
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
