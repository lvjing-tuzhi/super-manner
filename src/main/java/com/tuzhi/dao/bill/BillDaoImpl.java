package com.tuzhi.dao.bill;

import com.mysql.cj.util.StringUtils;
import com.tuzhi.dao.BaseDao;
import com.tuzhi.pojo.Bill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: superManager
 * @description: 订单的Dao的实现层
 * @author: 兔子
 * @create: 2021-11-14 09:44
 **/

public class BillDaoImpl implements BillDao{
//    得到订单列表
    @Override
    public List<Bill> getBillList(Connection connection,String queryProductName,String queryProviderId,String queryIsPayment) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<Bill> billList = new ArrayList<>();
        ArrayList<Object> list = new ArrayList<>();
        if (connection != null) {
            StringBuffer sql = new StringBuffer();
            sql.append("select b.*,p.proName as providerName from smbms_bill b,smbms_provider p where b.providerId=p.id");
            if (!StringUtils.isNullOrEmpty(queryProductName)) {
                sql.append(" and productName like ?");
                list.add("%"+queryProductName+"%");
            }else if (!StringUtils.isNullOrEmpty(queryProviderId) && Integer.parseInt(queryProviderId) > 0) {
                sql.append(" and providerId = ?");
                list.add(Integer.parseInt(queryProviderId));
            }else if (!StringUtils.isNullOrEmpty(queryIsPayment) && Integer.parseInt(queryIsPayment) > 0) {
                sql.append(" and isPayment = ?");
                list.add(Integer.parseInt(queryIsPayment));
            }
            Object[] param = list.toArray();
            rs = BaseDao.execute(connection, pstm, sql.toString(), param, rs);
            while (rs.next()) {
                Bill bill = new Bill();
                bill.setId(rs.getInt("id"));
                bill.setBillCode(rs.getString("billCode"));
                bill.setProductName(rs.getString("productName"));
                bill.setProductDesc(rs.getString("productDesc"));
                bill.setProductUnit(rs.getString("productUnit"));
                bill.setProductCount(rs.getBigDecimal("productCount"));
                bill.setTotalPrice(rs.getBigDecimal("totalPrice"));
                bill.setIsPayment(rs.getInt("isPayment"));
                bill.setCreatedBy(rs.getInt("createdBy"));
                bill.setCreationDate(rs.getDate("creationDate"));
                bill.setModifyBy(rs.getInt("modifyBy"));
                bill.setModifyDate(rs.getDate("modifyDate"));
                bill.setProviderId(rs.getInt("providerId"));
                bill.setProviderName(rs.getString("providerName"));
                billList.add(bill);
            }
            BaseDao.closeResource(null,pstm,rs);
        }
        return billList;
    }
}
