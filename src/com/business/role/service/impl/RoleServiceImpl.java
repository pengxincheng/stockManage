package com.business.role.service.impl;

import com.business.role.dao.RoleDao;
import com.business.role.po.Role;
import com.business.role.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by pxc on 2018/4/24.
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> getAllRoles(Role role) {
        return roleDao.getAllRoles(role);
    }

    @Override
    public void saveRole(Role role) {
        role.setCreateTime(new Date());
        roleDao.saveEntity(role);
    }

    @Override
    public void updateRole(Role role) {
        Role r = roleDao.getEntityById(role.getRoleId());
        if (StringUtils.isNotBlank(role.getRoleName())) {
            r.setRoleName(role.getRoleName());
        }
        if (StringUtils.isNotBlank(role.getRoleCode())) {
            r.setRoleCode(role.getRoleCode());
        }
    }

    @Override
    public void deleteRole(Role role) {
        roleDao.deleteEntity(role);
    }
}
