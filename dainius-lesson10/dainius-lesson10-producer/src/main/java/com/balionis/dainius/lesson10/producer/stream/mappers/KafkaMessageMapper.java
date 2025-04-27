package com.balionis.dainius.lesson10.producer.stream.mappers;

import com.balionis.dainius.lesson10.producer.generated.model.SendMessageRequest;
import com.balionis.dainius.lesson10.producer.stream.model.KafkaMessage;
import org.mapstruct.Mapper;

@Mapper(implementationPackage = "com.balionis.dainius.lesson10.producer.stream.mappers.impl")
public interface KafkaMessageMapper {
    KafkaMessage apply(SendMessageRequest message);
}
