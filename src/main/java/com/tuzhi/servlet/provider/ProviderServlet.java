package com.tuzhi.servlet.provider;

import com.tuzhi.pojo.Provider;
import com.tuzhi.pojo.Test;
import com.tuzhi.service.provider.ProviderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @program: superManager
 * @description: 供应商servlet
 * @author: 兔子
 * @create: 2021-11-14 14:15
 **/

public class ProviderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method.equals("query")) {
            queryProvider(req,resp);
        }
    }

//    查询供应商
    private void queryProvider(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("进入供应商");
        ProviderServiceImpl providerService = new ProviderServiceImpl();
        List<Provider> providerList = providerService.getProviderList();
        req.setAttribute("providerList",providerList);
        try {
            req.getRequestDispatcher("providerlist.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
