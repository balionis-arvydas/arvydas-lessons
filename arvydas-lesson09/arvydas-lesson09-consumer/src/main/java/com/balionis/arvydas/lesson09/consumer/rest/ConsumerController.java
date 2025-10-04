package com.balionis.arvydas.lesson09.consumer.rest;

import com.balionis.arvydas.lesson09.consumer.generated.api.ConsumerApi;
import com.balionis.arvydas.lesson09.consumer.generated.model.GetMessageResponse;
import com.balionis.arvydas.lesson09.consumer.service.ConsumerService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ConsumerController implements ConsumerApi {

    private final ConsumerService consumerService;

    @Override
    public ResponseEntity<GetMessageResponse> listMessages(
            @Parameter(name = "page", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "page", required = false) Integer page
    ) {
        log.info("page={}", page);
        var messages = consumerService.readMessages(Optional.ofNullable(page).orElse(0));
        log.info("messages={}", messages);
        return ResponseEntity.ok(new GetMessageResponse().messages(messages));
    }
}