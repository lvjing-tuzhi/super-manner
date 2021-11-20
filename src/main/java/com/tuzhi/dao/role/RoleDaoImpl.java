package com.tuzhi.dao.role;

import com.tuzhi.dao.BaseDao;
import com.tuzhi.pojo.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: superManager
 * @description: 角色的Dao层实现类
 * @author: 兔子
 * @create: 2021-11-12 12:40
 **/

public class RoleDaoImpl implements RoleDao{
    //获取角色列表
    @Override
    public List<Role> getRoleList(Connection connection) throws Exception {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<Role> roleList = new ArrayList<>();
        if (connection != null) {
            String sql = "select * from smbms_role";
            Object[] param = {};
            rs = BaseDao.execute(connection, pstm, sql, param, rs);
            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setRoleCode(rs.getString("roleCode"));
                role.setRoleName(rs.getString("roleName"));
                roleList.add(role);
            }
            BaseDao.closeResource(null,pstm,rs);
        }
        return roleList;
    }
}
