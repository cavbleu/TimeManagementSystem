package ru.egartech.tmsystem;

import jakarta.persistence.Entity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
public class TimeManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimeManagementSystemApplication.class, args);
    }

}
