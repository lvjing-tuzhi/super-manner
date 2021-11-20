package com.tuzhi.dao.provider;

import com.mysql.cj.util.StringUtils;
import com.tuzhi.dao.BaseDao;
import com.tuzhi.pojo.Provider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: superManager
 * @description: 服务商Dao
 * @author: 兔子
 * @create: 2021-11-14 12:14
 **/

public class ProviderDaoImpl implements ProviderDao{
    @Override
    public List<Provider> getProviderList(Connection connection) throws Exception {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<Provider> providerList = new ArrayList<>();
        if (connection != null) {
            String sql = "select * from smbms_provider";
            Object[] param = {};
            rs = BaseDao.execute(connection, pstm, sql.toString(), param, rs);
            while (rs.next()) {
                Provider provider = new Provider();
                provider.setId(rs.getInt("id"));
                provider.setProCode(rs.getString("proCode"));
                provider.setProName(rs.getString("proName"));
                provider.setProDesc(rs.getString("proDesc"));
                provider.setProContact(rs.getString("proContact"));
                provider.setProPhone(rs.getString("proPhone"));
                provider.setProAddress(rs.getString("proAddress"));
                provider.setProFax(rs.getString("proFax"));
                provider.setCreatedBy(rs.getInt("createdBy"));
                provider.setCreationDate(rs.getDate("creationDate"));
                provider.setModifyDate(rs.getDate("modifyDate"));
                provider.setModifyBy(rs.getInt("modifyBy"));
                providerList.add(provider);
            }
            BaseDao.closeResource(null,pstm,rs);
        }
        return providerList;
    }
}
