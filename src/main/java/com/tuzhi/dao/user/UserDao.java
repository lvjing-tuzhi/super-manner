package com.tuzhi.dao.user;

import com.tuzhi.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @program: superManager
 * @description: 用户数据库
 * @author: 兔子
 * @create: 2021-11-05 23:24
 **/

public interface UserDao {
//    得到要登录的用户
    public User getLoginUser(Connection connection,String userCode) throws SQLException;
}
