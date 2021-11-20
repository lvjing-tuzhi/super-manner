package com.tuzhi.dao.bill;

import com.tuzhi.pojo.Bill;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @program: superManager
 * @description: 订单Dao层
 * @author: 兔子
 * @create: 2021-11-14 09:43
 **/

public interface BillDao {
    //获取订单列表
    public List<Bill> getBillList(Connection connection,String queryProductName,String queryProviderId,String queryIsPayment) throws SQLException;
}
