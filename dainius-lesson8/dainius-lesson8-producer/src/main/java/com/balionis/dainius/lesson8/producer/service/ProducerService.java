package com.balionis.dainius.lesson8.producer.service;

import com.balionis.dainius.lesson8.producer.generated.model.SendMessageRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProducerService {
    public void sendMessage(SendMessageRequest request) {
        log.info("request={}", request);
    }
}