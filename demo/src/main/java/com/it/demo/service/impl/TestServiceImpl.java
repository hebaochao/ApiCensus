package com.it.demo.service.impl;

import com.common.api.census.annotation.ApiRequestMonitor;
import com.it.demo.handler.Max3ApiMonitorHandler;
import com.common.api.census.handler.impl.ApiMHCountHandler;
import com.common.api.census.model.MonitorRequestResult;
import com.it.demo.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
