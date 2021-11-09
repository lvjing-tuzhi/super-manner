package com.tuzhi.service.user;

import com.tuzhi.pojo.User;

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
}
