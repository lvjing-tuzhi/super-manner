package com.tuzhi.servlet.user;

import com.tuzhi.pojo.User;
import com.tuzhi.service.user.UserServiceImpl;
import com.tuzhi.tools.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @program: superManager
 * @description: user的servlet层接受
 * @author: 兔子
 * @create: 2021-11-06 20:49
 **/

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        获取账号密码
        String userCode = req.getParameter("userCode");
        String userPassword = req.getParameter("userPassword");
//        和数据库中的密码进行对比，调用业务层
        UserServiceImpl userService = new UserServiceImpl();
        User user = userService.login(userCode, userPassword);
        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute(Constants.USER_SESSION,user);
            resp.sendRedirect(req.getContextPath()+"/jsp/frame.jsp");
        }else {
            req.setAttribute("error","用户名或者账号不正确");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
