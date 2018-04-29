package com.business.user.dao.impl;

import com.business.user.dao.UserDao;
import com.business.user.enums.UserType;
import com.business.user.po.User;
import com.sysBasic.dao.impl.BasicDaoImpl;
import com.order.cc.sys.dao.FoHQLQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * Created by pxc on 2018/4/24.
 */
@Repository("userDao")
public class UserDaoImpl extends BasicDaoImpl<User> implements UserDao {
    @Override
    public List<User> getAllUser(User user) {
        FoHQLQuery query = new FoHQLQuery();
        String hql = "from User u ";
        if(UserType.employee == user.getUserType()){
            hql+=" left join fetch u.role r ";
        }
         hql+=" where 1=1 ";
        hql += this.getConditions(query, user);
        query.setHQL(hql);
        return execFoQuery(query);
    }

    private String getConditions(FoHQLQuery query, User user) {
        String conditions = "";
        if (StringUtils.isNotBlank(user.getUserCode())) {
            conditions += " and u.userCode= :userCode ";
            query.setString("userCode", user.getUserCode());
        }
        if (StringUtils.isNotBlank(user.getRoleId())) {
            conditions += " and u.roleId = :roleId ";
            query.setString("roleId", user.getRoleId());
        }
        if (StringUtils.isNotBlank(user.getPassword())) {
            conditions += " and u.password = :password ";
            query.setString("password", user.getPassword());
        }
        if (StringUtils.isNotBlank(user.getUserName())) {
            conditions += " and u.userName = :userName ";
            query.setString("userName", user.getUserName());
        }
        if (StringUtils.isNotBlank(user.getIsDelete())) {
            conditions += " and u.isDelete = :isDelete ";
            query.setString("isDelete", user.getIsDelete());
        }
        if (null != user.getUserType()) {
            conditions += " and u.userType = :useType ";
            query.setString("useType", user.getUserType().name());
        }
        return conditions;
    }
}
