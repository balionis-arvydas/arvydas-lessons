package com.balionis.arvydas.lesson5.rest;

import com.balionis.arvydas.lesson5.generated.api.MovingApi;
import com.balionis.arvydas.lesson5.generated.model.AddNumberRequest;
import com.balionis.arvydas.lesson5.generated.model.GetMovingResponse;
import com.balionis.arvydas.lesson5.service.MovingService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MovingController implements MovingApi {

    private final MovingService movingService;

    @Override
    public ResponseEntity<Void> addNumber(
            @Parameter(name = "AddNumberRequest") @Valid @RequestBody(required = false) AddNumberRequest request
    ) {
        log.info("request={}", request);
        movingService.add(request.getNumber());
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<GetMovingResponse> getMoving() {
        var movingAverage = movingService.getMovingAverage();

        var response = new GetMovingResponse();
        response.setNumber(movingAverage);

        log.info("response={}", response);

        return ResponseEntity.ok(response);
    }
}
