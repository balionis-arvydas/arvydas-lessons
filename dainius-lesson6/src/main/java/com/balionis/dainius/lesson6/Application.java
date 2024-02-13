package com.balionis.dainius.lesson6;

import com.balionis.dainius.lesson6.configuration.AppConfigurationProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppConfigurationProperties.class)
@Slf4j
public class Application {
    public static void main(String[] args) {
        log.info("starting");

        SpringApplication.run(Application.class, args);

        log.info("finishing");
    }
}

