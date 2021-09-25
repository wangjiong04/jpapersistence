package com.example.jpapersistence;

import com.example.jpapersistence.idstrategy.IdCompare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpapersistenceApplication {
    @Autowired
    private IdCompare idCompare;

    public static void main(String[] args) {
        SpringApplication.run(JpapersistenceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            idCompare.test();

        };
    }
}
