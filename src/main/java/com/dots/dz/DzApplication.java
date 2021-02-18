package com.dots.dz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class DzApplication {

    public static void main(String[] args) {
        SpringApplication.run(DzApplication.class, args);
    }

}
