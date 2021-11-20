package com.tuzhi.service.role;

import com.tuzhi.dao.BaseDao;
import com.tuzhi.dao.role.RoleDao;
import com.tuzhi.dao.role.RoleDaoImpl;
import com.tuzhi.pojo.Role;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

/**
 * @program: superManager
 * @description: 角色业务实现层
 * @author: 兔子
 * @create: 2021-11-12 12:50
 **/

public class RoleServiceImpl implements RoleService{

    private RoleDao roleDao;

    public RoleServiceImpl() {
        roleDao = new RoleDaoImpl();
    }

    //获得角色列表
    @Override
    public List<Role> getRoleList() {
        Connection connection = null;
        List<Role> roleList = null;
        try {
            connection = BaseDao.getConnection();
            roleList = roleDao.getRoleList(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return roleList;
    }



}
