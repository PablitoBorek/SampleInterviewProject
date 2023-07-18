package com.example.KamsoftProject;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class KamsoftProjectApplication implements CommandLineRunner {

    public static void main(String[] args) {
        log.info("STARTING THE APPLICATION");
        log.info("Simple project witch saves from a txt file to Database");
        log.info("To get all information type help, if you want to exit type quit");
        SpringApplication.run(KamsoftProjectApplication.class, args);
        log.info("APPLICATION FINISHED");
    }

    @Override
    public void run(String... args) {
    }
}
