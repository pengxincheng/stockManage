package com.business.product.po;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by pxc on 2018/4/25.
 * 商品详情
 */
@Entity
@Table(name = "tab_item")
public class Item {
    private String itemId;
    private String productId;        //商品id
    private BigDecimal inPrice;      //进价
    private BigDecimal outPrice;     //售价
    private String supplierId;       //供应商
    private String productTime;      //生产日期
    private Date inTime;            //入库时间
    private String inUserId;        //入库负责人
    private Date outTime;          // 出库时间
    private String outUserId;       //出库负责人
    private String remark;          //备注

    @Id
    @Column(name = "item_id")
    @GeneratedValue(generator = "itemGenerator")
    @GenericGenerator(name = "itemGenerator", strategy = "uuid")
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    @Column(name = "product_id")
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Column(name = "in_price")
    public BigDecimal getInPrice() {
        return inPrice;
    }

    public void setInPrice(BigDecimal inPrice) {
        this.inPrice = inPrice;
    }

    @Column(name = "out_price")
    public BigDecimal getOutPrice() {
        return outPrice;
    }

    public void setOutPrice(BigDecimal outPrice) {
        this.outPrice = outPrice;
    }

    @Column(name = "supplier_id")
    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    @Column(name = "product_time")
    public String getProductTime() {
        return productTime;
    }

    public void setProductTime(String productTime) {
        this.productTime = productTime;
    }

    @Column(name = "in_time")
    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    @Column(name = "in_user_id")
    public String getInUserId() {
        return inUserId;
    }

    public void setInUserId(String inUserId) {
        this.inUserId = inUserId;
    }

    @Column(name = "out_time")
    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    @Column(name = "out_user_id")
    public String getOutUserId() {
        return outUserId;
    }

    public void setOutUserId(String outUserId) {
        this.outUserId = outUserId;
    }

    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
