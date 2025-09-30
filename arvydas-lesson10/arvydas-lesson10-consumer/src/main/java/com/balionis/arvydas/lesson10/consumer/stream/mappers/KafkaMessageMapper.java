package com.balionis.arvydas.lesson10.consumer.stream.mappers;

import com.balionis.arvydas.lesson10.avro.generated.v1.KafkaMessage;
import com.balionis.arvydas.lesson10.consumer.generated.model.Message;

import java.util.UUID;

public class KafkaMessageMapper {
    public Message apply(KafkaMessage message) {
        return new Message()
                .messageId(UUID.fromString(message.getMessageId()))
                .message(message.getMessage());
    }
}
