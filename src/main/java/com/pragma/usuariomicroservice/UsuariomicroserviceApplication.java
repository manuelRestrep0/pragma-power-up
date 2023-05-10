package com.pragma.usuariomicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication()
@EnableFeignClients
public class UsuariomicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsuariomicroserviceApplication.class, args);
    }

}
