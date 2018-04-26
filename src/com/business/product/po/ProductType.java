package com.business.product.po;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by pxc on 2018/4/25.
 * 商品类型
 */
@Entity
@Table(name = "tab_product_type")
public class ProductType {

    private String typeId;
    private String typeName;

    private String createUserId;    //创建人
    private Date createTime;       //创建时间
    private String updateUserId;     //修改人
    private Date updateTime;        //修改时间

    @Id
    @Column(name = "product_type_id")
    @GeneratedValue(generator = "productTypeGenerator")
    @GenericGenerator(name = "productTypeGenerator", strategy = "uuid")
    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    @Column(name = "type_name")
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Column(name = "create_user_id")
    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "update_user_id")
    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    @Column(name = "update_time")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
