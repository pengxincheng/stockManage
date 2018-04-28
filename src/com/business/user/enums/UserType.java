package com.business.user.enums;

/**
 * Created by pxc on 2018/4/28.
 */
public enum UserType {
    employee("员工"),
    supplier("供应商"),
    customer("客户");

    private String desc;

    UserType(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
