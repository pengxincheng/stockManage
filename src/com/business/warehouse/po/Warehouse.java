package com.business.warehouse.po;

import com.business.user.po.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by pxc on 2018/4/25.
 * 仓库
 */
@Entity
@Table(name = "tab_warehouse")
public class Warehouse {
    private String id;
    private String name;            //仓库名称
    private String address;          //地址
    private String desc;                 //描述
    private String createUserId;    //创建人
    private Date createTime;       //创建时间
    private String updateUserId;     //修改人
    private Date updateTime;        //修改时间
    private User createUser;

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "warehouseGenerator")
    @GenericGenerator(name = "warehouseGenerator", strategy = "uuid")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "warehouse_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "warehouse_desc")
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "create_user_id", nullable = false, insertable = false, updatable = false)
    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }
}
