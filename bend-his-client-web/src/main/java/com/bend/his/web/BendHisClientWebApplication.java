package com.bend.his.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.bend.his"})
@Slf4j
public class BendHisClientWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(BendHisClientWebApplication.class, args);
    }

}
