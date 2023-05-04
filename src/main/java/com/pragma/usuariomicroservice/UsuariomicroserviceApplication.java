package com.pragma.usuariomicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication()
public class UsuariomicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsuariomicroserviceApplication.class, args);
    }

}
