package com.tuzhi.dao.role;

import com.tuzhi.pojo.Role;

import java.sql.Connection;
import java.util.List;

/**
 * @program: superManager
 * @description: 角色类Dao层
 * @author: 兔子
 * @create: 2021-11-12 12:40
 **/

public interface RoleDao {
    //获取角色列表
    public List<Role> getRoleList(Connection connection) throws Exception;
}
