package com.common.api.census.aspect;

import com.common.api.census.annotation.ApiRequestMonitor;
import com.common.api.census.handler.ApiMonitortHandler;
import com.common.api.census.model.JoinPointHandler;
import com.common.api.census.model.MonitorRequestResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author : baochaohe
 * @version : v1.0
 * @description TODO api请求处理切面
 * @date : 2020/10/11 23:16
 */
@Aspect
@Slf4j
@Component
public class ApiRequestAspect {

  @Autowired
  private ApplicationContext applicationContext;
  /*
   **
   * 未执行返回默认错误消息
   */
  private Serializable defaultResult = new MonitorRequestResult<String>(-1, "api not execute", null);

  @Around(value = "@annotation(com.common.api.census.annotation.ApiRequestMonitor)")
  public Object around(ProceedingJoinPoint pjd) throws Throwable {
    Object result = null;
    JoinPointHandler joinPointHandler = JoinPointHandler.buildJoinPointParam(pjd);
    log.info("call :" + joinPointHandler.toString());
    ApiRequestMonitor myAnnotation = joinPointHandler.getMethod().getAnnotation(ApiRequestMonitor.class);
//    log.info(myAnnotation.type() + "...");// 参数p0的值
    ApiMonitortHandler apiMonitortHandler = this.createApiMonitortHandler(myAnnotation);
    boolean checkResult = apiMonitortHandler != null ? apiMonitortHandler.checkApi(joinPointHandler.getMethod(), joinPointHandler.getParams(), myAnnotation.type(), myAnnotation.isPersist()) : true;
    if (checkResult) {
      result = joinPointHandler.executeMethod();
    } else {
      result = apiMonitortHandler != null ? apiMonitortHandler.returnFailResult(joinPointHandler.getMethod(), joinPointHandler.getParams(), checkResult, myAnnotation.type(), myAnnotation.isPersist()) : this.defaultResult;
    }
    return result;
  }

  private ApiMonitortHandler createApiMonitortHandler(ApiRequestMonitor myAnnotation) {
    ApiMonitortHandler apiMonitortHandler = null;
    try {
      apiMonitortHandler = this.applicationContext.getBean(myAnnotation.handler().getSimpleName().toLowerCase(),myAnnotation.handler());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return apiMonitortHandler;
  }

}
