package com.tuzhi.servlet.user;


import com.tuzhi.pojo.User;
import com.tuzhi.service.user.UserServiceImpl;
import com.tuzhi.tools.Constants;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: superManager
 * @description:
 * @author: 兔子
 * @create: 2021-11-08 22:49
 **/

public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("开始保存");
        Object attribute = req.getSession().getAttribute(Constants.USER_SESSION);
        String rnewpassword = req.getParameter("rnewpassword");
        System.out.println("attribute:"+((User) attribute).getId());
        System.out.println("pw" + rnewpassword);
        if (attribute != null && rnewpassword != null) {
            UserServiceImpl userService = new UserServiceImpl();
            boolean b = userService.updatePw(((User) attribute).getId(), rnewpassword);
            System.out.println("是否修改成功"+b);
            if (b) {
                req.setAttribute(Constants.SYS_MESSAGE,"修改密码成功，请退出，使用新密码使用");
                req.getSession().removeAttribute(Constants.USER_SESSION);
            }else {
                req.setAttribute(Constants.SYS_MESSAGE,"修改密码错误，请重新修改");
            }
        }else {
            req.setAttribute(Constants.SYS_MESSAGE,"错误");
            System.out.println("错误");
        }
        req.getRequestDispatcher("pwdmodify.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
