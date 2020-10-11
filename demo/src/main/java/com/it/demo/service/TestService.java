package com.it.demo.service;

import com.common.api.census.model.MonitorRequestResult;

/**
 * @author : baochaohe
 * @version : v1.0
 * @description TODO
 * @date : 2020/10/11 23:16
 */
public interface TestService {

    public MonitorRequestResult testCall(String name);

    public MonitorRequestResult testVoidcountCallNumber(Integer age);

}
