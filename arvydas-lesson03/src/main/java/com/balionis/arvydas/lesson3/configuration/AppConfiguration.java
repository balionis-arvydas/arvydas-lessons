package com.balionis.arvydas.lesson3.configuration;

import com.balionis.arvydas.lesson3.service.AvgMovingServiceFunction;
import com.balionis.arvydas.lesson3.service.MovingService;
import com.balionis.arvydas.lesson3.service.MovingServiceFunction;
import com.balionis.arvydas.lesson3.service.SumMovingServiceFunction;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AppConfiguration {

    private final AppConfigurationProperties appConfigurationProperties;

    @Bean
    @ConditionalOnProperty(value = "app.service.function", havingValue = "avg", matchIfMissing = true)
    public MovingServiceFunction avgMovingServiceFunction() {
        return new AvgMovingServiceFunction();
    }

    @Bean
    @ConditionalOnProperty(value = "app.service.function", havingValue = "sum")
    public MovingServiceFunction sumMovingServiceFunction() {
        return new SumMovingServiceFunction();
    }

    @Bean
    public MovingService movingService(MovingServiceFunction movingServiceFunction) {
        return new MovingService(movingServiceFunction, appConfigurationProperties.getLimit());
    }
}
