package com.example.webmd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServingWebContentApplication {
///просто старт сервера
    public static void main(String[] args)
    {
        SpringApplication.run(ServingWebContentApplication.class, args);
    }

}