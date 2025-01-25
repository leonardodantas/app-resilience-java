package com.br.app.movie.tmdb.java;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Slf4j
@EnableFeignClients
@SpringBootApplication
@EnableAspectJAutoProxy
public class Application {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("Application started");
    }

}
