package com.business.role.service;

import com.business.role.po.Role;

import java.util.List;

/**
 * Created by pxc on 2018/4/24.
 * 角色service
 */
public interface RoleService {
    /**
     * 获取角色列表
     *
     * @param role
     * @return
     */
    List<Role> getAllRoles(Role role);

    /**
     * 添加角色
     *
     * @param role
     */
    void saveRole(Role role);

    /**
     * 修改角色
     *
     * @param role
     */
    void updateRole(Role role);

    /**
     * 删除角色
     *
     * @param role
     */
    void deleteRole(Role role);
}
