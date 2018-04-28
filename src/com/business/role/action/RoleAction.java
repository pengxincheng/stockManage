package com.business.role.action;

import com.business.role.po.Role;
import com.business.role.service.RoleService;
import com.business.user.action.UserAction;
import com.business.user.po.User;
import com.business.user.service.UserService;
import com.sysBasic.action.BasicAction;
import com.utils.JSONUtils;
import com.utils.Response;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by pxc on 2018/4/26.
 */
@Namespace("/role")
@ParentPackage("json-default")
public class RoleAction extends BasicAction {

    private final Logger logger = LoggerFactory.getLogger(UserAction.class);

    @Autowired
    private RoleService roleService;

    @Action(value = "list", results = {@Result(name = "success", type = "json", params = {"root", "response"})})
    public String getAllUser() {
        try {
            if (null == role) {
                role = new Role();
            }
            List<Role> roles = roleService.getAllRoles(role);
            response = Response.ok(JSONUtils.toJSONInclude(roles, "createUserId", "createTime", "role", "roleCode", "roleId", "roleName"));
        } catch (Exception e) {
            response = Response.error();
            logger.error(e.getMessage(), e);
        }
        return SUCCESS;
    }

    @Action(value = "add", results = {@Result(name = "success", type = "json", params = {"root", "response"})})
    public String addRole() {
        try {
            roleService.saveRole(role);
        } catch (Exception e) {
            response = Response.error();
            logger.error(e.getMessage(), e);
        }
        return SUCCESS;
    }

    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
