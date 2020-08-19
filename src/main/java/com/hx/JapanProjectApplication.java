package com.hx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hx.dao")
public class JapanProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(JapanProjectApplication.class, args);
    }

}
