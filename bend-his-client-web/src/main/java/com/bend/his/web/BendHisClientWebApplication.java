package com.bend.his.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.bend.his"})
public class BendHisClientWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(BendHisClientWebApplication.class, args);
    }

}
