package com.balionis.arvydas.lesson12.consumer.rest;

import com.balionis.arvydas.lesson12.consumer.generated.api.HeartbeatApi;
import com.balionis.arvydas.lesson12.consumer.generated.model.GetHeartbeatResponse;
import com.balionis.arvydas.lesson12.consumer.service.HeartbeatService;
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
