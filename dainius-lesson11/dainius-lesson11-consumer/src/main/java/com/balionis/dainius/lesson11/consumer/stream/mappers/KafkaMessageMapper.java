package com.balionis.dainius.lesson11.consumer.stream.mappers;

import com.balionis.dainius.lesson11.avro.generated.v1.KafkaMessage;
import com.balionis.dainius.lesson11.consumer.generated.model.Message;

import java.util.UUID;

public class KafkaMessageMapper {
    public Message apply(KafkaMessage message) {
        return new Message()
                .messageId(UUID.fromString(message.getMessageId()))
                .message(message.getMessage());
    }
}
