package com.balionis.arvydas.lesson8.producer.stream.mappers;

import com.balionis.arvydas.lesson8.producer.generated.model.SendMessageRequest;
import com.balionis.arvydas.lesson8.producer.stream.model.KafkaMessage;
import org.mapstruct.Mapper;

@Mapper(implementationPackage = "com.balionis.arvydas.lesson8.producer.stream.mappers.impl")
public interface KafkaMessageMapper {
    KafkaMessage apply(SendMessageRequest message);
}
