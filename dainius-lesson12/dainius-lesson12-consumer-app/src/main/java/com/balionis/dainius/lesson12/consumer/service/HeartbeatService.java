package com.balionis.dainius.lesson12.consumer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.OffsetDateTime;

@RequiredArgsConstructor
@Slf4j
public class HeartbeatService {

    private final String name;

    public OffsetDateTime checkStatus() {
        log.info("name={}", name);
        return OffsetDateTime.now();
    }
}