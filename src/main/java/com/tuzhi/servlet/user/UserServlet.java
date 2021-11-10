package com.tuzhi.servlet.user;


import com.alibaba.fastjson.JSONArray;
import com.mysql.cj.util.StringUtils;
import com.mysql.cj.xdevapi.JsonArray;
import com.tuzhi.pojo.User;
import com.tuzhi.service.user.UserServiceImpl;
import com.tuzhi.tools.Constants;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: superManager
 * @description:
 * @author: 兔子
 * @create: 2021-11-08 22:49
 **/

public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method.equals("savepwd") && method!=null) {
            updatePwd(req,resp);
        }else if (method.equals("pwdmodify")) {
            pwdModify(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
    //修改密码
    public void updatePwd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
            req.setAttribute(Constants.SYS_MESSAGE,"新密码有问题");
            System.out.println("错误");
        }
        req.getRequestDispatcher("pwdmodify.jsp").forward(req,resp);
    }
//    验证旧密码,与session进行对比
    public void pwdModify(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("进入验证旧密码");
        String oldpassword = req.getParameter("oldpassword");
        Object o = req.getSession().getAttribute(Constants.USER_SESSION);

//        用map存储转为json返回前端
        Map<String, String> hashMap = new HashMap<>();
//        session过期或者不存在
        if (o == null) {
            hashMap.put("result","sessionerror");
        }else if (((User)o).getUserPassword().equals(oldpassword)) {
            hashMap.put("result","true");
        }else if (!((User)o).getUserPassword().equals(oldpassword)) {
            hashMap.put("result","false");
        }else if (StringUtils.isNullOrEmpty(oldpassword)) {
            hashMap.put("result","error");
        }
        resp.setContentType("application/json");
        try {
            PrintWriter writer = resp.getWriter();
            writer.write(JSONArray.toJSONString(hashMap));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
