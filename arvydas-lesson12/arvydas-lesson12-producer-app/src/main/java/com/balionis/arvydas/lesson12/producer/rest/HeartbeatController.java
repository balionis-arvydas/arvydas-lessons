package com.balionis.arvydas.lesson12.producer.rest;

import com.balionis.arvydas.lesson12.producer.generated.api.HeartbeatApi;
import com.balionis.arvydas.lesson12.producer.generated.model.GetHeartbeatResponse;
import com.balionis.arvydas.lesson12.producer.service.HeartbeatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HeartbeatController implements HeartbeatApi {

    private final HeartbeatService heartbeatService;

    @Override
    public ResponseEntity<GetHeartbeatResponse> getHeartbeat() {
        log.info("alive...");
        var when = heartbeatService.checkStatus();
        return ResponseEntity.ok(new GetHeartbeatResponse().checkTs(when));
    }
}
