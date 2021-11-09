package com.tuzhi.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @program: superManager
 * @description: 操作数据库的基本
 * @author: 兔子
 * @create: 2021-11-04 22:56
 **/

public class BaseDao {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    static {
        Properties properties = new Properties();
        InputStream is = BaseDao.class.getClassLoader().getResourceAsStream("db.properties");
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver = properties.getProperty("driver");
        url = properties.getProperty("url");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
    }

    //获取数据库的连接
    public static Connection getConnection() throws ClassNotFoundException {
        Class.forName(driver);
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    //获取数据库查询公共类
    public static ResultSet execute(Connection connection,PreparedStatement preparedStatement,String sql,Object[] parm,ResultSet resultSet) {
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        for (int i = 0; i < parm.length; i++) {
            try {
                preparedStatement.setObject(i+1,parm[i]);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        try {
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

    public static int execute(Connection connection,PreparedStatement pstm,
                              String sql,Object[] params) throws Exception{
        int updateRows = 0;
        pstm = connection.prepareStatement(sql);
        for(int i = 0; i < params.length; i++){
            pstm.setObject(i+1, params[i]);
        }
        updateRows = pstm.executeUpdate();
        return updateRows;
    }

//    释放资源
    public static boolean closeResource(Connection connection,PreparedStatement preparedStatement,ResultSet resultSet) {
        boolean flag = true;
        if (resultSet != null) {
            try {
                resultSet.close();
                resultSet = null;
            } catch (SQLException throwables) {
                flag = false;
                throwables.printStackTrace();
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
                preparedStatement = null;
            } catch (SQLException throwables) {
                flag = false;
                throwables.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException throwables) {
                flag = false;
                throwables.printStackTrace();
            }
        }
        return flag;
    }
}

