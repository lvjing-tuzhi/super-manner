package com.tuzhi.service.user;

import com.tuzhi.pojo.User;

import java.util.List;

/**
 * @program: superManager
 * @description: user的业务层
 * @author: 兔子
 * @create: 2021-11-06 19:54
 **/

public interface UserService {
//    用户登录
    public User login(String userCode,String password);
//    修改密码
    public boolean updatePw(int id,String pwd);
//    获得用户数量
    public int getUserCount(String userName,int userRole);
//    获得用户列表
    public List<User> getUserList(String userName,int userRole,int currentPageNo,int pageSize);
//    根据用户id获取用户信息
    public User getUserById(int id);
    //添加用户
    public boolean addUser(User user);
    //根据userCode获得用户列表，也可用于验证userCode存不存在
    public User selectUserCodeExist(String userCode);
    //修改用户
    public boolean modifyUser(User user);
//    根据id删除用户
    public boolean deleteUser(int id);
}
