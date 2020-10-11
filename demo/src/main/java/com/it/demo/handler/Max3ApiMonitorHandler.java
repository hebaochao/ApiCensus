package com.it.demo.handler;


import com.common.api.census.handler.impl.ApiMHCountHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author : baochaohe
 * @version : v1.0
 * @description TODO 自定义实现计数和限制接口handler的demo
 * @date : 2020/10/11 23:16
 */
@Component("max3apimonitorhandler")
public class Max3ApiMonitorHandler extends ApiMHCountHandler {


    public Max3ApiMonitorHandler() {
        super();
        super.maxCount = 3L;
    }


    @Override
    public boolean checkApi(Method method, Map<String, Object> params, int type, boolean isPersist) {

        boolean result = super.checkApi(method, params, type, isPersist);
        if (!result) {
            return result;
        }

        if (isPersist) {  //持久化

        }

        return result;
    }


}
