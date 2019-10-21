package com.hlau.fenliu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;


@SpringBootApplication
@Async
@EnableGlobalMethodSecurity(securedEnabled = true)
public class FenliuApplication {

    public static void main(String[] args) {
        SpringApplication.run(FenliuApplication.class, args);
    }

}
