package com.tuzhi.servlet.user;


import com.alibaba.fastjson.JSONArray;
import com.mysql.cj.util.StringUtils;
import com.mysql.cj.xdevapi.JsonArray;
import com.tuzhi.pojo.Role;
import com.tuzhi.pojo.User;
import com.tuzhi.service.role.RoleServiceImpl;
import com.tuzhi.service.user.UserServiceImpl;
import com.tuzhi.tools.Constants;
import com.tuzhi.tools.PageSupport;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
        }else if (method.equals("query")) {
            query(req,resp);
        }else if (method.equals("view")) {
            view(req,resp);
        }else if (method.equals("add")) {
            try {
                addUser(req,resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if (method.equals("getrolelist")) {
            getrolelist(req,resp);
        }else if (method.equals("ucexist")) {
            ucexist(req,resp);
        }else if (method.equals("modify")) {
            getUserById(req,resp);
        }else if (method.equals("modifyexe")) {
            modifyexe(req,resp);
        }else if (method.equals("deluser")) {
            deluser(req,resp);
        }
    }

    private void deluser(HttpServletRequest req, HttpServletResponse resp) {
        int uid = Integer.parseInt(req.getParameter("uid"));
        UserServiceImpl userService = new UserServiceImpl();
        Map<String, String> map = new HashMap<>();
        if (uid <= 0) {
            map.put("delResult","notexist");
            System.out.println("删除不存在");
        }
        if (userService.deleteUser(uid)) {
            map.put("delResult","true");
            System.out.println("删除成功");
        }else {
            map.put("delResult","false");
            System.out.println("删除失败");
        }
        resp.setContentType("application/JSON");
        PrintWriter writer = null;
        try {
            writer = resp.getWriter();
            writer.write(JSONArray.toJSONString(map));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void modifyexe(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("开始修改代码");
        String uid = req.getParameter("uid");
        String userName = req.getParameter("userName");
        String gender = req.getParameter("gender");
        String birthday = req.getParameter("birthday");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        String userRole = req.getParameter("userRole");

        User user = new User();
        user.setId(Integer.parseInt(uid));
        user.setUserName(userName);
        user.setGender(Integer.parseInt(gender));
        try {
            user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setPhone(phone);
        user.setAddress(address);
        user.setUserRole(Integer.parseInt(userRole));
        user.setModifyBy(((User)req.getSession().getAttribute(Constants.USER_SESSION)).getId());
        user.setModifyDate(new java.sql.Date(new Date().getTime()));

        UserServiceImpl userService = new UserServiceImpl();
        if (userService.modifyUser(user)) {
            resp.sendRedirect(req.getContextPath()+"/jsp/user.do?method=query");
            System.out.println("修改成功");
        }else {
            System.out.println("修改失败");
            try {
                req.getRequestDispatcher("usermodify.jsp").forward(req,resp);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }

    }

//    根据userCode获得用户信息
    private void getUserById(HttpServletRequest req, HttpServletResponse resp) {
        String uid = req.getParameter("uid");
        UserServiceImpl userService = new UserServiceImpl();
        User user = userService.getUserById(Integer.parseInt(uid));
        req.setAttribute("user",user);
        try {
            req.getRequestDispatcher("usermodify.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ucexist(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userCode = req.getParameter("userCode");
        System.out.println("进入检查用户代码有没有存在" + userCode);
        UserServiceImpl userService = new UserServiceImpl();
        User user = userService.selectUserCodeExist(userCode);
        HashMap<String, String> resultMap = new HashMap<>();
        System.out.println(user == null);
        if (user == null) {
            resultMap.put("userCode","noexist");
        }else {
            resultMap.put("userCode","exist");
        }
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.write(JSONArray.toJSONString(resultMap));
        writer.flush();
        writer.close();
    }

    //    获取角色列表
    private void getrolelist(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("开始查询角色列表");
        RoleServiceImpl roleService = new RoleServiceImpl();
        List<Role> roleList = roleService.getRoleList();
//        把roleList对象转为json格式输出
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.write(JSONArray.toJSONString(roleList));
        writer.flush();
        writer.close();
    }

    //    添加用户
    private void addUser(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String userCode = req.getParameter("userCode");
        String userName = req.getParameter("userName");
        String ruserPassword = req.getParameter("ruserPassword");
        String gender = req.getParameter("gender");
        String birthday = req.getParameter("birthday");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        String userRole = req.getParameter("userRole");
        System.out.println("用户编码:"+userCode);

        User user = new User();
        user.setUserCode(userCode);
        System.out.println("servlet:"+user.getUserCode());
        user.setUserName(userName);
        user.setUserPassword(ruserPassword);
        user.setGender(Integer.parseInt(gender));
        user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
        user.setPhone(phone);
        user.setAddress(address);
        user.setUserRole(Integer.parseInt(userRole));
        user.setModifyDate(new Date("yyyy-MM-dd hh:mm:ss"));
        user.setModifyBy(((User)req.getSession().getAttribute(Constants.USER_SESSION)).getId());
        System.out.println(user.getUserCode());

        UserServiceImpl userService = new UserServiceImpl();
        if (userService.addUser(user)) {
            resp.sendRedirect(req.getContextPath()+"/jsp/user.do?method=query");
        }else {
            req.getRequestDispatcher("useradd.jsp").forward(req,resp);
        }

    }

    //    查看用户管理
    private void view(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uid = req.getParameter("uid");
        System.out.println("进入view" + uid);
        UserServiceImpl userService = new UserServiceImpl();
        User user = userService.getUserById(Integer.parseInt(uid));
        req.setAttribute("user",user);
        req.getRequestDispatcher("userview.jsp").forward(req,resp);
    }

    //查询用户管理
    private void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        获取前端参数
        String queryname = req.getParameter("queryname");
        String queryUserRoleTemp = req.getParameter("queryUserRole");
        String pageIndex = req.getParameter("pageIndex");


//        初始页数
        int queryUserRole = 0;
        int pageSize = Constants.PAGESIZE;
        int currentPageNo = 1;

        if (queryname == null) {
            queryname = "";
        }
        if (queryUserRoleTemp != null) {
            queryUserRole = Integer.parseInt(queryUserRoleTemp);
        }
        if (pageIndex != null) {
            currentPageNo = Integer.parseInt(pageIndex);
        }

        UserServiceImpl userService = new UserServiceImpl();
//        总数量
        int totalCount = userService.getUserCount(queryname, queryUserRole);
//        总页数
//        PageSupport pageSupport = new PageSupport();
//        pageSupport.setTotalCount(pageSize);
//        pageSupport.setCurrentPageNo(currentPageNo);
//        pageSupport.setPageSize(pageSize);
        int totalPageCount = ((int) totalCount / pageSize ) + 1;

//        获取用户展示列表
        List<User> userList = userService.getUserList(queryname, queryUserRole, currentPageNo, pageSize);

//        获得角色列表
        RoleServiceImpl roleService = new RoleServiceImpl();
        List<Role> roleList = roleService.getRoleList();

//        返回数据给前端
        req.setAttribute("userList",userList);
        req.setAttribute("roleList",roleList);
        req.setAttribute("queryUserName",queryname);
        req.setAttribute("totalPageCount",totalPageCount);
        req.setAttribute("totalCount",totalCount);
        req.setAttribute("currentPageNo",currentPageNo);

        req.getRequestDispatcher("userlist.jsp").forward(req,resp);
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);

    }
}
