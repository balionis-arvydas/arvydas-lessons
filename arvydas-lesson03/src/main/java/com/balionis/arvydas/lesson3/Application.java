package com.balionis.arvydas.lesson3;

import com.balionis.arvydas.lesson3.configuration.AppConfigurationProperties;
import com.balionis.arvydas.lesson3.service.MovingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
@EnableConfigurationProperties(AppConfigurationProperties.class)
@Slf4j
public class Application {

    public static void main(String[] args) {
        log.info("starting");

        SpringApplication.run(Application.class, args);

        log.info("finishing");
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            log.info("args={}", Arrays.stream(args).toList());

            var service = ctx.getBean(MovingService.class);
            Arrays.stream(args).map(Double::parseDouble).forEach(service::add);

            log.info("movingAverage={}", service.getMovingAverage());
        };
    }

}

