package com.balionis.dainius.lesson8.producer.stream.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Data
@Builder
@Jacksonized
public class KafkaMessage {
    private UUID messageId;
    private String message;
}
