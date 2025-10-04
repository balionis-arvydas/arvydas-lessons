package com.balionis.arvydas.lesson09.consumer.stream.mappers;

import com.balionis.arvydas.lesson09.consumer.generated.model.Message;
import com.balionis.arvydas.lesson09.consumer.stream.model.KafkaMessage;
import org.mapstruct.Mapper;

@Mapper(implementationPackage = "com.balionis.arvydas.lesson09.consumer.stream.mappers.impl")
public interface KafkaMessageMapper {
    Message apply(KafkaMessage message);
}
