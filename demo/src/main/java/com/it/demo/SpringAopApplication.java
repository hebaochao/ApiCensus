package com.it.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan(basePackages  = {"com.common.api.census.*","com.it.demo.*"})
public class SpringAopApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringAopApplication.class, args);
  }

}

