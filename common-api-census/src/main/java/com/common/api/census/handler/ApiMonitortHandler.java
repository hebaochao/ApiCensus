package com.common.api.census.handler;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Map;

public interface ApiMonitortHandler {


  /***
   * 检查api判断逻辑
   * @param method
   * @param params
   * @param type
   * @param isPersist
   * @return   true 执行，false 中断 进入默认错误结果返回
   */
  public  boolean  checkApi(Method method, Map<String, Object> params, int type, boolean isPersist);
  /***
   * 定义处理错误/超出限制/其他默认错误消息
   * @param method
   * @param params
   * @param result
   * @param type
   * @param isPersist
   * @return
   */
  public Serializable returnFailResult(Method method, Map<String, Object> params, boolean result, int type, boolean isPersist);
}
