package com.example.springgettingstarted;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class SpringGettingStartedApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringGettingStartedApplication.class, args);
    }

}
