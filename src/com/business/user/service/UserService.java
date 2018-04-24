package com.business.user.service;

import com.business.user.po.User;

import java.util.List;

/**
 * Created by pxc on 2018/4/24.
 */
public interface UserService {

    /**
     * 查询用户列表
     *
     * @param user
     * @return
     */
    List<User> getAllUser(User user);

    /**
     * 添加用户
     * @param user
     */
    void saveUser(User user);

    /**
     * 校验用户登陆
     * @param user
     * @return
     */
    boolean checkLogin(User user);
}
