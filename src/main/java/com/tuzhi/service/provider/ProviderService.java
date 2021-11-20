package com.tuzhi.service.provider;

import com.tuzhi.pojo.Provider;

import java.util.List;

/**
 * @program: superManager
 * @description: 供应商业务层
 * @author: 兔子
 * @create: 2021-11-14 12:24
 **/

public interface ProviderService {
//    获得供应商列表
    public List<Provider> getProviderList();
}
