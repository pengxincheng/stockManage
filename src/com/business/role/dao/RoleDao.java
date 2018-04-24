package com.business.role.dao;

import com.business.role.po.Role;
import com.sysBasic.dao.BasicDao;

import java.util.List;

/**
 * Created by pxc on 2018/4/24.
 * 角色dao
 */
public interface RoleDao extends BasicDao<Role> {

    /**
     * 获取角色列表
     * @param role
     * @return
     */
    List<Role> getAllRoles(Role role);
}
