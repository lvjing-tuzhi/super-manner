package com.tuzhi.dao.user;

import com.tuzhi.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @program: superManager
 * @description: 用户数据库
 * @author: 兔子
 * @create: 2021-11-05 23:24
 **/

public interface UserDao {
//    得到要登录的用户
    public User getLoginUser(Connection connection,String userCode) throws SQLException;
    //修改密码
    public int updatePwd(Connection connection,int id,String pw) throws Exception;
//    获取用户个数
    public int getUserCount(Connection connection,String userName,int userRole) throws Exception;
//    获得用户列表
    public List<User> getUserList(Connection connection,String userName,int userRole,int currentPageNo,int pageSize) throws Exception;
    //根据用户id获取用户信息
    public User getUserById(Connection connection,int id) throws Exception;
//    添加用户
    public int addUser(Connection connection,User user) throws Exception;
//    修改用户
    public int modifyUser(Connection connection,User user) throws Exception;
//    删除用户
    public int deleteUser(Connection connection,int id) throws Exception;
}


