package com.it.demo.service;

import com.common.api.census.model.MonitorRequestResult;

public interface TestService {

  public MonitorRequestResult testCall(String name);

  public MonitorRequestResult testVoidcountCallNumber(Integer age);

}
