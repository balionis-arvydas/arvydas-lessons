package com.balionis.arvydas.lesson12.producer.rest;

import com.balionis.arvydas.lesson12.producer.generated.api.ProducerApi;
import com.balionis.arvydas.lesson12.producer.generated.model.AddMessageRequest;
import com.balionis.arvydas.lesson12.producer.generated.model.AddMessageResponse;
import com.balionis.arvydas.lesson12.producer.service.ProducerService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProducerController implements ProducerApi {

    private final ProducerService producerService;

    @Override
    public ResponseEntity<AddMessageResponse> addMessage(
            @Parameter(name = "AddMessageRequest", description = "") @Valid @RequestBody(required = false)
            AddMessageRequest addMessageRequest
    ) {
        log.info("addMessageRequest={}", addMessageRequest);
        var addMessageResponse = producerService.addMessage(addMessageRequest);
        log.info("addMessageResponse={}", addMessageResponse);

        return ResponseEntity.ok(addMessageResponse);
    }
}
