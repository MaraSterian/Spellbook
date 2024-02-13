package com.example.spellbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EnableScheduling
@SpringBootApplication
public class SpellbookApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpellbookApplication.class, args);
    }

}
