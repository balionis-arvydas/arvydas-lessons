package com.balionis.dainius.lesson6.configuration;

import com.balionis.dainius.lesson6.repository.PetRepository;
import com.balionis.dainius.lesson6.service.PetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class AppConfiguration {

    private final AppConfigurationProperties appConfigurationProperties;

    @Bean
    public PetService petService(PetRepository petRepository) {
        return new PetService(appConfigurationProperties.getName(), petRepository);
    }
}
