package com.balionis.arvydas.lesson12.producer.service;

import com.balionis.arvydas.lesson12.producer.generated.model.AddMessageRequest;
import com.balionis.arvydas.lesson12.producer.generated.model.AddMessageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProducerService {

    public AddMessageResponse addMessage(AddMessageRequest addMessageRequest) {
        log.info("addMessageRequest={}", addMessageRequest);
        return new AddMessageResponse().messageId(UUID.randomUUID());
    }
}