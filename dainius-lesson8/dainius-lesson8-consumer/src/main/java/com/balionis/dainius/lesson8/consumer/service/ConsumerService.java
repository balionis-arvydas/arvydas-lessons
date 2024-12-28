package com.balionis.dainius.lesson8.consumer.service;

import com.balionis.dainius.lesson8.consumer.generated.model.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class ConsumerService {

    private final int pageSize;

    public List<Message> readMessages(int page) {
        log.info("pageSize={}, page={}", pageSize, page);
        return List.of();
    }
}