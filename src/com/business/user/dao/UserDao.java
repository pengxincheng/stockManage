package com.business.user.dao;

import com.business.user.po.User;
import com.sysBasic.dao.BasicDao;

import java.util.List;

/**
 * Created by pxc on 2018/4/24.
 */
public interface UserDao extends BasicDao<User> {

    /**
     * 查询用户列表
     *
     * @param user
     * @return
     */
    List<User> getAllUser(User user);
}
