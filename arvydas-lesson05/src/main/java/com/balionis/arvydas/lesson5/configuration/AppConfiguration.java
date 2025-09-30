package com.balionis.arvydas.lesson5.configuration;

import com.balionis.arvydas.lesson5.service.AvgMovingServiceFunction;
import com.balionis.arvydas.lesson5.service.MovingService;
import com.balionis.arvydas.lesson5.service.MovingServiceFunction;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AppConfiguration {

    private final AppConfigurationProperties appConfigurationProperties;

    @Bean
    public MovingServiceFunction avgMovingServiceFunction() {
        return new AvgMovingServiceFunction();
    }

    @Bean
    public MovingService movingService(MovingServiceFunction movingServiceFunction) {
        return new MovingService(movingServiceFunction, appConfigurationProperties.getLimit());
    }
}
