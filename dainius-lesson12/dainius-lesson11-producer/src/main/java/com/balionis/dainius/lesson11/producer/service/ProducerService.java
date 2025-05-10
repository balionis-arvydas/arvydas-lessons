package com.balionis.dainius.lesson11.producer.service;

import com.balionis.dainius.lesson11.producer.generated.model.SendMessageRequest;
import com.balionis.dainius.lesson11.producer.stream.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProducerService {

    @Autowired
    private final KafkaProducer kafkaProducer;

    public void sendMessage(SendMessageRequest request) {
        log.info("request={}", request);
        kafkaProducer.sendMessage(request);
    }
}