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

    @Override
    public boolean updatePw(int id, String pwd) {
        Connection connection = null;
        boolean flag = false;
        try {
            connection = BaseDao.getConnection();
            int i = userDao.updatePwd(connection, id, pwd);
            System.out.println(i);
            if (i > 0) {
                flag = true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

}
