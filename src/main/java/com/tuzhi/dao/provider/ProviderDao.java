package com.tuzhi.dao.provider;

import com.tuzhi.pojo.Provider;

import java.sql.Connection;
import java.util.List;

/**
 * @program: superManager
 * @description: 提供商Dao层
 * @author: 兔子
 * @create: 2021-11-14 12:14
 **/

public interface ProviderDao {
    //获取提供商列表
    public List<Provider> getProviderList(Connection connection) throws Exception;

}
