package com.tuzhi.service.provider;

import com.tuzhi.dao.BaseDao;
import com.tuzhi.dao.provider.ProviderDao;
import com.tuzhi.dao.provider.ProviderDaoImpl;
import com.tuzhi.pojo.Provider;

import java.sql.Connection;
import java.util.List;

/**
 * @program: superManager
 * @description: 供应商业务实现层
 * @author: 兔子
 * @create: 2021-11-14 12:25
 **/

public class ProviderServiceImpl implements ProviderService{
    private ProviderDao providerDao;
    public ProviderServiceImpl() {
        providerDao = new ProviderDaoImpl();
    }

    @Override
    public List<Provider> getProviderList() {
        Connection connection = null;
        List<Provider> providerList = null;
        try {
            connection  = BaseDao.getConnection();
            providerList = providerDao.getProviderList(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return providerList;
    }
}
