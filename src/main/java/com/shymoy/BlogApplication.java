package com.shymoy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 博客系统启动类
 * 
 * @Author: shymoy
 */
@SpringBootApplication
public class BlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
        System.out.println("========================================");
        System.out.println("博客系统启动成功！");
        System.out.println("访问地址：http://localhost:8080/api");
        System.out.println("========================================");
    }
}
