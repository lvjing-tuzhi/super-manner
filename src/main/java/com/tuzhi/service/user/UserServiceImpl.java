package com.tuzhi.service.user;

import com.tuzhi.dao.BaseDao;
import com.tuzhi.dao.user.UserDao;
import com.tuzhi.dao.user.UserDaoImpl;
import com.tuzhi.pojo.User;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @program: superManager
 * @description: 用户业务层实现类
 * @author: 兔子
 * @create: 2021-11-06 19:56
 **/

public class UserServiceImpl implements UserService{

//    业务层都会调用dao层，所以我们要引入Dao层
    private UserDao userDao;

    public UserServiceImpl() {
        userDao = new UserDaoImpl();
    }

    /**
     * 登录
     * @param userCode
     * @param password
     * @return
     */
    @Override
    public User login(String userCode, String password) {
        Connection connection = null;
        User user = null;
        try {
            connection = BaseDao.getConnection();
            user = userDao.getLoginUser(connection, userCode);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return user;
    }

@Test
    public void test(){
        UserServiceImpl userService = new UserServiceImpl();
        User admin = userService.login("admin", "123456");
        System.out.println(admin);
        System.out.println(admin.getUserPassword());
}
}
