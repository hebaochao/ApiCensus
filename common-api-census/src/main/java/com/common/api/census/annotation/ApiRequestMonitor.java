package com.common.api.census.annotation;

import com.common.api.census.handler.ApiMonitortHandler;
import org.springframework.context.annotation.ComponentScan;

import java.lang.annotation.*;

/**
 * @Target 此注解的作用目标，括号里METHOD的意思说明此注解只能加在方法上面
 * @Retention 注解的保留位置，括号里RUNTIME的意思说明注解可以存在于运行时，可以用于反射
 * @Documented 说明该注解将包含在javadoc中
 *
 * 1.@interface自定义注解
 * <1>@interface自定义注解自动继承了java.lang.annotation.Annotation接口,由编译程序自动完成其他细节。
 * <2>在定义注解时,不能继承其他的注解或接口。
 * <3>使用@interface来声明一个注解,
 * 1>.每一个方法实际上是声明了一个配置参数,
 * 2>.方法的名称就是参数的名称,
 * 3>.返回值类型就是参数的类型,(返回值类型只能是基本类型、Class、String、enum)
 * 4>.可以通过default来声明参数的默认值
 * 2.举例说明,分别作用在类,方法,属性上的注解
 *
 */

/**
 * @author : baochaohe
 * @version : v1.0
 * @description TODO api请求监听注解
 * @date : 2020/10/11 23:16
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiRequestMonitor {

  /***
   * 类型
   */
  int type() default  0;

  /***
   * 是否持久化
   */
    boolean isPersist() default false;

  /***
   * handler
   */
  Class<? extends ApiMonitortHandler> handler() default  ApiMonitortHandler.class;







}
