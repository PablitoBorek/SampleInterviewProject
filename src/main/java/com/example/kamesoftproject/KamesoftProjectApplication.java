package com.example.kamesoftproject;

import com.example.kamesoftproject.model.User;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.convert.ConversionService;


import org.slf4j.Logger;

@SpringBootApplication
public class KamesoftProjectApplication implements CommandLineRunner {

    String path = "src/main/resources/Users.txt";

    @Autowired
    private ConversionService conversionService;

    private static Logger log = LoggerFactory
            .getLogger(KamesoftProjectApplication.class);

    public static void main(String[] args) {
        log.info("STARTING THE APPLICATION");
        log.info("Simple project witch saves from a txt file to Database");
        log.info("To get all information type help, if you want to exit type quit");
        SpringApplication.run(KamesoftProjectApplication.class, args);
        log.info("APPLICATION FINISHED");
    }

    @Override
    public void run(String... args) {
    }
}
