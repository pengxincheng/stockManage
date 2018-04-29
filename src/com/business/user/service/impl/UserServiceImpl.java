package com.business.user.service.impl;

import com.business.user.dao.UserDao;
import com.business.user.enums.UserType;
import com.business.user.po.User;
import com.business.user.service.UserService;
import com.utils.PasswordUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
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
        user.setIsDelete("F");
        user.setUserType(user.getUserType());
        return userDao.getAllUser(user);
    }

    @Override
    public void saveUser(User user) {
        User currentUser = (User) ServletActionContext.getRequest().getSession().getAttribute("currentUser");
        user.setCreateUserId(currentUser.getUserId());
        user.setCreateTime(new Date());
        user.setIsDelete("F");
        //默认密码123456
        if (UserType.employee == user.getUserType()) {
            user.setPassword(PasswordUtil.EncoderByMd5("123456"));
        }
        userDao.saveEntity(user);
    }

    @Override
    public User checkLogin(User user) {
        user.setPassword(PasswordUtil.EncoderByMd5(user.getPassword()));
        user.setIsDelete("F");
        user.setUserType(UserType.employee);
        List<User> users = userDao.getAllUser(user);
        return users.size() > 0 ? users.get(0) : null;
    }

    @Override
    public User getById(String id) {
        return userDao.getEntityById(id);
    }

    @Override
    public void updateUser(User user) {
        User entity = userDao.getEntityById(user.getUserId());
        if (StringUtils.isNotBlank(user.getUserAlias())) {
            entity.setUserAlias(user.getUserAlias());
        }
        if (StringUtils.isNotBlank(user.getRoleId())) {
            entity.setRoleId(user.getRoleId());
        }
        if (StringUtils.isNotBlank(user.getAddress())) {
            entity.setAddress(user.getAddress());
        }
        if (StringUtils.isNotBlank(user.getTel())) {
            entity.setTel(user.getTel());
        }
        if (StringUtils.isNotBlank(user.getUserName())) {
            entity.setUserName(user.getUserName());
        }
        userDao.updateEntity(entity);
    }

    @Override
    public void delUser(String userId) {
        User user = userDao.getEntityById(userId);
        user.setIsDelete("T");
        userDao.updateEntity(user);
    }
}
