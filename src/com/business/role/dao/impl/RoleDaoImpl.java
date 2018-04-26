package com.business.role.dao.impl;

import com.business.role.dao.RoleDao;
import com.business.role.po.Role;
import com.sysBasic.dao.impl.BasicDaoImpl;
import com.order.cc.sys.dao.FoHQLQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by pxc on 2018/4/24.
 */
@Repository("roleDao")
public class RoleDaoImpl extends BasicDaoImpl<Role> implements RoleDao {
    @Override
    public List<Role> getAllRoles(Role role) {
            FoHQLQuery query = new FoHQLQuery();
            String hql = "from Role r where 1=1 ";
            if (StringUtils.isNotBlank(role.getRoleCode())) {
                hql += "and r.roleCode = :roleCode ";
                query.setString("roleCode", role.getRoleCode());
            }
            if (StringUtils.isNotBlank(role.getRoleName())) {
                hql += " and r.roleName like :roleName ";
                query.setString("roleName", "%" + role.getRoleName() + "%");
            }
            query.setHQL(hql);
        return execFoQuery(query);
    }
}
