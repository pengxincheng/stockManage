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
     *
     * @param user
     */
    void saveUser(User user);

    /**
     * 校验用户登陆
     *
     * @param user
     * @return
     */
    User checkLogin(User user);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    User getById(String id);

    /**
     * 更新
     *
     * @param user
     */
    void updateUser(User user);

    /**
     * 删除（逻辑删除）
     * @param userId
     */
    void delUser(String userId);

}
