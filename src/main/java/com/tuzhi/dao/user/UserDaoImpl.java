package com.tuzhi.dao.user;

import com.mysql.cj.util.StringUtils;
import com.tuzhi.dao.BaseDao;
import com.tuzhi.pojo.User;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

//    获得用户数
    @Override
    public int getUserCount(Connection connection, String userName, int userRole) throws Exception {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int count = 0;
        if (connection != null) {
            StringBuffer sql = new StringBuffer();
            sql.append("select count(1) as count from smbms_user u,smbms_role r where u.userRole = r.id");
            ArrayList<Object> userList = new ArrayList<>();
            //进行sql语句拼接
            if (!StringUtils.isNullOrEmpty(userName)){
                sql.append(" and u.userName like ?");
                userList.add("%"+userName+"%");
            }else if (userRole > 0) {
                sql.append(" and u.userRole = ?");
                userList.add(userRole);
            }
            Object[] param = userList.toArray();
            rs = BaseDao.execute(connection, pstm, sql.toString(), param, rs);
            if(rs.next()) {
                count = rs.getInt("count");
            }
            BaseDao.closeResource(null,pstm,rs);
        }
        return count;
    }

//    获得用户列表

    @Override
    public List<User> getUserList(Connection connection, String userName, int userRole, int currentPageNo, int pageSize) throws Exception {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<User> userList = new ArrayList<>();
        if (connection != null) {
            StringBuffer sql = new StringBuffer();
            ArrayList<Object> list = new ArrayList<>();
            sql.append("select u.*,r.roleName as userRoleName from smbms_user u,smbms_role r where u.userRole = r.id");
            //进行sql语句拼接
            if (!StringUtils.isNullOrEmpty(userName)) {
                sql.append(" and userName like ?");
                list.add("%"+userName+"%");
            }else if (userRole > 0) {
                sql.append(" and u.userRole = ?");
                list.add(userRole);
            }
            sql.append(" order by creationDate DESC limit ?,?");
            currentPageNo = (currentPageNo - 1) * pageSize;
            list.add(currentPageNo);
            list.add(pageSize);
            Object[] parm = list.toArray();
            rs = BaseDao.execute(connection, pstm,sql.toString(),parm,rs);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("userName"));
                user.setUserCode(rs.getString("userCode"));
                user.setUserRole(rs.getInt("userRole"));
                user.setGender(rs.getInt("gender"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPhone(rs.getString("phone"));
                user.setUserRoleName(rs.getString("userRoleName"));
                userList.add(user);
            }
            BaseDao.closeResource(null,pstm,rs);
        }
        return userList;
    }

    //根据用户id获取到用户信息
    @Override
    public User getUserById(Connection connection, int id) throws Exception {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        User user = null;
        if (connection != null) {
            String sql = "select u.*,r.roleName as userRoleName from smbms_user u,smbms_role r where u.id=? and u.userRole = r.id";
            Object[] parm = {id};
            rs = BaseDao.execute(connection, pstm, sql, parm, rs);
//            把数据取出来
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("userName"));
                user.setUserCode(rs.getString("userCode"));
                user.setUserPassword(rs.getString("userPassword"));
                user.setGender(rs.getInt("gender"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setUserRole(rs.getInt("userRole"));
                user.setUserRoleName(rs.getString("userRoleName"));
                user.setCreatedBy(rs.getInt("createdBy"));
                user.setCreationDate(rs.getDate("creationDate"));
                user.setModifyBy(rs.getInt("modifyBy"));
                user.setModifyDate(rs.getDate("modifyDate"));
            }
            BaseDao.closeResource(null,pstm,rs);
        }
        return user;
    }

//    添加用户
    @Override
    public int addUser(Connection connection, User user) throws Exception {
        PreparedStatement pstm = null;
        int execute = 0;
        if (connection != null) {
            String sql = "insert into smbms_user (userCode,userName,userPassword," +
                    "userRole,gender,birthday,phone,address,creationDate,createdBy) " +
                    "values(?,?,?,?,?,?,?,?,?,?)";
            Object[] parm = {user.getUserCode(),user.getUserName(),user.getUserPassword(),
            user.getUserRole(),user.getGender(),user.getBirthday(),user.getPhone(),user.getAddress(),
            user.getCreationDate(),user.getCreatedBy()};

            execute = BaseDao.execute(connection, pstm, sql, parm);
            BaseDao.closeResource(null,pstm,null);
        }
        return execute;
    }

//    修改用户
    @Override
    public int modifyUser(Connection connection, User user) throws Exception {
        PreparedStatement pstm = null;
        int execute = 0;
        if (connection != null) {
            String sql = "update smbms_user set userName=?,"+
                    "gender=?,birthday=?,phone=?,address=?,userRole=?,modifyBy=?,modifyDate=? where id = ? ";
            Object[] params = {user.getUserName(),user.getGender(),user.getBirthday(),user.getPhone(),
            user.getAddress(),user.getUserRole(),user.getModifyBy(),user.getModifyDate(),user.getId()};
            System.out.println("userDao" + user);
            execute = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null,pstm,null);
        }
        return execute;
    }

//    删除用户
    @Override
    public int deleteUser(Connection connection, int id) throws Exception {
        PreparedStatement pstm = null;
        int execute = 0;
        if (connection != null) {
            String sql = "delete from smbms_user where id=?";
            Object[] param = {id};
            execute = BaseDao.execute(connection, pstm, sql, param);

            BaseDao.closeResource(null,pstm,null);
        }
        return execute;
    }
}
