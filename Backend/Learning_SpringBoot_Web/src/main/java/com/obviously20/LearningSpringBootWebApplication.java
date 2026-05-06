package com.obviously20;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//其中包含@ComponentScan注解，默认扫描当前包及其子包下的所有组件
public class LearningSpringBootWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearningSpringBootWebApplication.class, args);
    }

}
