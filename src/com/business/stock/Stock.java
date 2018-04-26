package com.business.stock;


import javax.persistence.Entity;
import javax.persistence.Table;

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
    private Integer inCount;
    private Integer outCount;
    private String isWaring;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getWareHouseId() {
        return wareHouseId;
    }

    public void setWareHouseId(String wareHouseId) {
        this.wareHouseId = wareHouseId;
    }

    public Integer getInCount() {
        return inCount;
    }

    public void setInCount(Integer inCount) {
        this.inCount = inCount;
    }

    public Integer getOutCount() {
        return outCount;
    }

    public void setOutCount(Integer outCount) {
        this.outCount = outCount;
    }

    public String getIsWaring() {
        return isWaring;
    }

    public void setIsWaring(String isWaring) {
        this.isWaring = isWaring;
    }
}
