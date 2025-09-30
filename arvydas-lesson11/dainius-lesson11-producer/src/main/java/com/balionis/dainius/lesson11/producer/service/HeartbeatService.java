package com.balionis.dainius.lesson11.producer.service;

import org.springframework.http.HttpStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;

@RequiredArgsConstructor
@Slf4j
public class HeartbeatService {

    private final String name;

    public HttpStatusCode checkStatus() {
        log.info("name={}", name);
        return HttpStatus.OK;
    }
}
