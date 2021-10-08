package com.example.jpapersistence.idstrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.example.jpapersistence")
public class IdApplication {
    @Autowired
    private IdCompare idCompare;

    public static void main(String[] args) {
        SpringApplication.run(IdApplication.class, args);
    }

    @Bean
    public CommandLineRunner idApplicationInit(ApplicationContext ctx) {
        return args -> {
            //            idCompare.insertSequence();
            //            idCompare.insertUUID();
            //            idCompare.insertUUIDUpdate();
            //            idCompare.insertSnowFlake();
            //            idCompare.getById();
            //            idCompare.getByUuid();
            idCompare.generateAssociate();
        };
    }
}
