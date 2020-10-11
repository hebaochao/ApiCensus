package com.it.demo.controller;

import com.common.api.census.model.MonitorRequestResult;
import com.it.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : baochaohe
 * @version : v1.0
 * @description TODO 测试控制器
 * @date : 2020/10/11 23:16
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;


    @GetMapping("/countCall/{name}")
    public MonitorRequestResult countCall(@PathVariable("name") String name) {
        return this.testService.testCall(name);
    }

    @GetMapping("/countAge/{age}")
    public MonitorRequestResult testVoidcountCallNumber(@PathVariable("age") Integer age) {
        return this.testService.testVoidcountCallNumber(age);
    }


}
