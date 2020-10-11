package com.common.api.census.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : baochaohe
 * @version : v1.0
 * @description TODO 封装切点处理模型
 * @date : 2020/10/11 23:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Slf4j
public class JoinPointHandler implements Serializable {

  /***
   * 方法
   */
  private Method method;
  /***
   * 参数列表以及其属性
   */
  private Map<String, Object> params = new LinkedHashMap<>();
  private ProceedingJoinPoint pjd;
  private MethodSignature signature;

  /***
   * 便捷构建
   * @param pjd
   * @return
   */
  public static JoinPointHandler buildJoinPointParam(ProceedingJoinPoint pjd) {
    JoinPointHandler joinPointParam = new JoinPointHandler();
    /*获取signature 该注解作用在方法上，强转为 MethodSignature*/
    MethodSignature signature = (MethodSignature) pjd.getSignature();
    joinPointParam.setParams(getJoinPointRequestParams(pjd));
    joinPointParam.setSignature(signature);
    joinPointParam.setPjd(pjd);
    // 获取的是代理类的method对象
//    这个aop拦截的是ServiceImpl的一个方法，然后这个ServiceImpl又启动了事务管理，而事务管理又是基于AOP的。
//    也就是说，这个权限的@Around的切面拦截的是个代理对象的方法，而代理对象的方法是不会把原来父类中的方法的注解加上去的，所以这里这个注解的对象为null。
    Method method = signature.getMethod();
    try {
      // 这个方法才是目标对象上有注解的方法
      method = pjd.getTarget().getClass().getDeclaredMethod(signature.getName(), method.getParameterTypes());
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    }
    joinPointParam.setMethod(method);
    return joinPointParam;
  }

  /****
   * 获取切点中的方法参数
   * @param pjd
   * @return
   */
  public static Map<String, Object> getJoinPointRequestParams(ProceedingJoinPoint pjd) {
    Object[] values = pjd.getArgs();
    /*获取signature 该注解作用在方法上，强转为 MethodSignature*/
    MethodSignature signature = (MethodSignature) pjd.getSignature();
    /*参数名称数组(与args参数值意义对应)*/
    String[] parameterNames = signature.getParameterNames();            //  [i] 参数名称
    Map<String, Object> params = new HashMap<String, Object>();
    if (parameterNames != null && values.length == parameterNames.length) {
      for (int i = 0; i < parameterNames.length; i++) {
        params.put(parameterNames[i], values[i]);
      }
    }
    return params;
  }

  /***
   * 執行方法
   * @return
   * @throws Throwable
   */
  public Object executeMethod() throws Throwable {
    return this.executeMethod(null);
  }

  /***
   * 执行方法
   * @param params
   * @return
   * @throws Throwable
   */
  public Object executeMethod(Object[] params) throws Throwable {
    Object result = null;
    /*获取返回值类型*/
    Class returnType = this.signature.getReturnType();
    if (returnType == Void.TYPE) { //执行目标方法
      this.pjd.proceed();
    } else {
      Object[] args = params == null ? this.pjd.getArgs() : params;
      result = this.pjd.proceed(args);
    }
    return result;
  }

  /***
   * 获取参数值
   * @param paramName
   * @return
   */
  public Object getParamByKey(String paramName) {
    if (!this.params.containsKey(paramName)) {
      throw new RuntimeException("not exit param name!");
    }
    return this.params.get(paramName);
  }

}
