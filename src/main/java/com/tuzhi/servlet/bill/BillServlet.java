package com.tuzhi.servlet.bill;

import com.tuzhi.pojo.Bill;
import com.tuzhi.pojo.Provider;
import com.tuzhi.service.bill.BillServiceImpl;
import com.tuzhi.service.provider.ProviderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @program: superManager
 * @description: 订单的servlet层
 * @author: 兔子
 * @create: 2021-11-14 10:09
 **/

public class BillServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method.equals("query")) {
            queryBill(req,resp);
        }
    }

    //订单列表
    private void queryBill(HttpServletRequest req, HttpServletResponse resp) {
//       获取前端搜索参数
        String queryProductName = req.getParameter("queryProductName");
        String queryProviderId = req.getParameter("queryProviderId");
        String queryIsPayment = req.getParameter("queryIsPayment");

        System.out.println("queryProductName: " + queryProductName + " queryProviderId: " + queryProviderId + " queryIsPayment: " + queryIsPayment);

        //获取订单列表
        BillServiceImpl billService = new BillServiceImpl();
        List<Bill> billList = billService.getBillList(queryProductName,queryProviderId,queryIsPayment);
//        获取供应商列表
        ProviderServiceImpl providerService = new ProviderServiceImpl();
        List<Provider> providerList = providerService.getProviderList();
        req.setAttribute("billList",billList);
        req.setAttribute("providerList",providerList);
        try {
            req.getRequestDispatcher("billlist.jsp").forward(req,resp);
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
