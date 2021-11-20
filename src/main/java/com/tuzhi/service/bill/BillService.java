package com.tuzhi.service.bill;

import com.tuzhi.pojo.Bill;

import java.util.List;

/**
 * @program: superManager
 * @description: 订单业务层
 * @author: 兔子
 * @create: 2021-11-14 10:04
 **/

public interface BillService {
    //获取订单列表
    public List<Bill> getBillList(String queryProductName,String queryProviderId,String queryIsPayment);
}
