package com.business.stock.po;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by pxc on 2018/4/25.
 * <p>
 * 库存日志 记录每次入库出库信息
 */
@Entity
@Table(name = "tab_stock_log")
public class StockLog {

    private String id;
    private String stockId;
    private String productId;
    private String wareHouseId;
    private Integer inCount;
    private Integer outCount;
    private String isWaring;
    private String userId;
    private String createTime;
    private String type;      //类型：in:入库   out出库
    private BigDecimal totalMoney;   //交易总额
    private String remark;


    @Id
    @Column(name = "stock_log_id")
    @GeneratedValue(generator = "StockGenerator")
    @GenericGenerator(name = "StockGenerator", strategy = "uuid")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "stock_id")
    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
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

    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "create_time")
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "total_money")
    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
