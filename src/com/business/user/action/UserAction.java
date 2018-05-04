package com.business.user.action;

import com.business.user.enums.UserType;
import com.business.user.po.User;
import com.business.user.service.UserService;
import com.sysBasic.action.BasicAction;
import com.utils.JSONUtils;
import com.utils.PasswordUtil;
import com.utils.Response;
import org.apache.commons.lang3.StringUtils;
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
 * Created by pxc on 2018/4/24.
 */
@Namespace("/user")
@ParentPackage("json-default")
public class UserAction extends BasicAction {

    private final Logger logger = LoggerFactory.getLogger(UserAction.class);

    @Autowired
    private UserService userService;

    /**
     * 员工列表
     *
     * @return
     */
    @Action(value = "list", results = {@Result(name = "success", type = "json", params = {"root", "response"})})
    public String getAllUser() {
        try {
            if (null == user) {
                user = new User();
            }
            List<User> users = userService.getAllUser(user);
            response = Response.ok(JSONUtils.toJSONInclude(users, "userId", "userCode", "userName", "userAlias", "tel", "address", "remark", "createUserId", "createTime", "role", "roleId", "roleName"));
        } catch (Exception e) {
            response = Response.error();
            logger.error(e.getMessage(), e);
        }
        return SUCCESS;
    }

    @Action(value = "login", results = {@Result(name = "success", type = "json", params = {"root", "response"})})
    public String login() {
        try {
            User currentUser = userService.checkLogin(user);
            if (null != currentUser) {
                ServletActionContext.getRequest().getSession().setAttribute("currentUser", currentUser);
                response = Response.ok();
            } else {
                response = Response.error();
            }

        } catch (Exception e) {
            response = Response.error();
            logger.error(e.getMessage(), e);
        }
        return SUCCESS;
    }

    @Action(value = "logout", results = {@Result(name = "success", type = "redirect", location = "/login.jsp")})
    public String logout() {
        ServletActionContext.getRequest().getSession().setAttribute("currentUser", null);
        return SUCCESS;
    }

    /**
     * 添加员工
     *
     * @return
     */
    @Action(value = "add", results = {@Result(name = "success", type = "json", params = {"root", "response"})})
    public String addUser() {
        try {
            if (StringUtils.isNotEmpty(user.getUserId())) {
                userService.updateUser(user);
            } else {
                userService.saveUser(user);
            }
            response = Response.ok();
        } catch (Exception e) {
            response = Response.error();
            logger.error(e.getMessage(), e);
        }
        return SUCCESS;
    }

    @Action(value = "delete", results = {@Result(name = "success", type = "json", params = {"root", "response"})})
    public String delUser() {
        try {
            userService.delUser(id);
            response = Response.ok();
        } catch (Exception e) {
            response = Response.error();
            logger.error(e.getMessage(), e);
        }
        return SUCCESS;
    }

    @Action(value = "getById", results = {@Result(name = "success", type = "json", params = {"root", "response"})})
    public String getById() {
        try {
            if (null == id) {
                response = Response.error();
            }
            User u = userService.getById(id);
            response = Response.ok(JSONUtils.toJSONInclude(u, "userId", "userCode", "userName", "userAlias", "tel", "address", "remark", "createUserId", "createTime", "roleId"));
        } catch (Exception e) {
            response = Response.error();
            logger.error(e.getMessage(), e);
        }
        return SUCCESS;
    }

    @Action(value = "changePassword", results = {@Result(name = "success", type = "json", params = {"root", "response"})})
    public String changePassword() {
        try {
            User user = (User) ServletActionContext.getRequest().getSession().getAttribute("currentUser");
            User u = userService.getById(user.getUserId());
            if (u.getPassword().equals(PasswordUtil.EncoderByMd5(oldPwd))) {
                u.setPassword(newPwd);
                userService.updateUser(u);
                response = Response.ok();
            } else {
                response = Response.error("原密码不正确");
            }

        } catch (Exception e) {
            response = Response.error();
            logger.error(e.getMessage(), e);
        }
        return SUCCESS;
    }

    private User user;
    private String id;
    private String newPwd;
    private String oldPwd;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }
}
