package com.tuzhi.service.role;

import com.tuzhi.pojo.Role;

import java.sql.Connection;
import java.util.List;

/**
 * @program: superManager
 * @description: 角色业务层
 * @author: 兔子
 * @create: 2021-11-12 12:49
 **/

public interface RoleService {
    //获得角色列表
    public List<Role> getRoleList();
}
