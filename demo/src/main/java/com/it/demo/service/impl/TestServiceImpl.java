package com.it.demo.service.impl;

import com.common.api.census.annotation.ApiRequestMonitor;
import com.common.api.census.handler.impl.ApiMHCountHandler;
import com.common.api.census.model.MonitorRequestResult;
import com.it.demo.handler.Max3ApiMonitorHandler;
import com.it.demo.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author : baochaohe
 * @version : v1.0
 * @description TODO 测试服务，增加注解是实现接口监听调用次数和调用限制
 * @date : 2020/10/11 23:16
 */
@Service
@Slf4j
public class TestServiceImpl implements TestService {


    @Override
    @ApiRequestMonitor(type = 1, isPersist = true, handler = ApiMHCountHandler.class)
    public MonitorRequestResult testCall(String name) {
        return new MonitorRequestResult<String>(200, "success", ("hello" + name));
    }


    @Override
    @ApiRequestMonitor(type = 100, isPersist = true, handler = Max3ApiMonitorHandler.class)
    public MonitorRequestResult testVoidcountCallNumber(Integer age) {
        return new MonitorRequestResult<String>(200, "success", ("hello"));
    }

}
