package com.business.user.service.impl;

import com.business.user.dao.UserDao;
import com.business.user.po.User;
import com.business.user.service.UserService;
import com.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by pxc on 2018/4/24.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getAllUser(User user) {
        return userDao.getAllUser(user);
    }

    @Override
    public void saveUser(User user) {
        user.setCreateTime(new Date());
        //默认密码123456
        user.setPassword(PasswordUtil.EncoderByMd5("123456"));
        userDao.saveEntity(user);
    }

    @Override
    public boolean checkLogin(User user) {
        user.setPassword(PasswordUtil.EncoderByMd5(user.getPassword()));
        List<User> users = userDao.getAllUser(user);
        return users.size() > 0 ? true : false;
    }
}