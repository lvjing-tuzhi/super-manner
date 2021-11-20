package com.tuzhi.service.bill;

import com.tuzhi.dao.BaseDao;
import com.tuzhi.dao.bill.BillDao;
import com.tuzhi.dao.bill.BillDaoImpl;
import com.tuzhi.pojo.Bill;
import com.tuzhi.pojo.Provider;
import com.tuzhi.service.provider.ProviderServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @program: superManager
 * @description: 订单业务层实现类
 * @author: 兔子
 * @create: 2021-11-14 10:04
 **/

public class BillServiceImpl implements BillService{
    private BillDao billDao;
    public BillServiceImpl() {
        billDao = new BillDaoImpl();
    }

    //获取订单列表
    @Override
    public List<Bill> getBillList(String queryProductName,String queryProviderId,String queryIsPayment) {
        Connection connection = null;
        List<Bill> billList = null;
        try {
            connection  = BaseDao.getConnection();
            billList = billDao.getBillList(connection,queryProductName,queryProviderId,queryIsPayment);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return billList;
    }
}
