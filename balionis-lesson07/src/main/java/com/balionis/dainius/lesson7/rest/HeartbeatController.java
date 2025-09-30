package com.balionis.dainius.lesson7.rest;

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

    @GetMapping("/heartbeat")
    HttpEntity<Void> heartbeat() {
        log.info("alive...");
        return ResponseEntity.ok().build();
    }
}
