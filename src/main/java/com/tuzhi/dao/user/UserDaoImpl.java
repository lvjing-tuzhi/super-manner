package com.tuzhi.dao.user;

import com.tuzhi.dao.BaseDao;
import com.tuzhi.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @program: superManager
 * @description: 用户dao层实现类
 * @author: 兔子
 * @create: 2021-11-05 23:27
 **/

public class UserDaoImpl implements UserDao{
//    得到要登录的类
    @Override
    public User getLoginUser(Connection connection, String userCode) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        User user = null;
        if(connection != null) {
            String sql = "select * from smbms_user where userCode=?";
            Object[] parm = {userCode};
            ResultSet execute = BaseDao.execute(connection, pstm, sql, parm, rs);
            if(execute.next()) {
                user = new User();
                user.setId(execute.getInt("id"));
                user.setUserCode(execute.getString("userCode"));
                user.setUserName(execute.getString("userName"));
                user.setUserPassword(execute.getString("userPassword"));
                user.setGender(execute.getInt("gender"));
                user.setBirthday(execute.getDate("birthday"));
                user.setPhone(execute.getString("phone"));
                user.setAddress(execute.getString("address"));
                user.setUserRole(execute.getInt("userRole"));
                user.setCreatedBy(execute.getInt("createdBy"));
                user.setCreationDate(execute.getDate("creationDate"));
                user.setModifyBy(execute.getInt("modifyBy"));
                user.setModifyDate(execute.getDate("modifyDate"));
            }
            BaseDao.closeResource(connection,pstm,execute);
        }

        return user;
    }

//    修改密码
    @Override
    public int updatePwd(Connection connection,int id, String pw) throws Exception {
        String sql = "update smbms_user set userPassword = ? where id = ?";
        PreparedStatement pstm = null;
        int execute = 0;
        if (connection != null) {
            Object[] parm = {pw,id};
            execute = BaseDao.execute(connection,pstm, sql, parm);
            BaseDao.closeResource(null,pstm,null);
        }

        return execute;
    }
}
