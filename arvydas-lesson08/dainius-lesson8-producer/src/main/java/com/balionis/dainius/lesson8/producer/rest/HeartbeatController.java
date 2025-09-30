package com.balionis.dainius.lesson8.producer.rest;

import com.balionis.dainius.lesson8.producer.service.HeartbeatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class HeartbeatController {

    private final HeartbeatService heartbeatService;

    @GetMapping("/heartbeat")
    HttpEntity<Void> heartbeat() {
        log.info("alive...");
        var status = heartbeatService.checkStatus();
        return ResponseEntity.status(status).build();
    }
}
