package com.manage.field;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.manage.field.mapper")
public class FieldApplication {

    public static void main(String[] args) {
        SpringApplication.run(FieldApplication.class, args);
    }

}
