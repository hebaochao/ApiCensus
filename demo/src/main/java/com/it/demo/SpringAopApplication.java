package com.it.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author : baochaohe
 * @version : v1.0
 * @description TODO  main
 * @date : 2020/10/11 23:16
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.common.api.census.*", "com.it.demo.*"})
public class SpringAopApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAopApplication.class, args);
    }

}

