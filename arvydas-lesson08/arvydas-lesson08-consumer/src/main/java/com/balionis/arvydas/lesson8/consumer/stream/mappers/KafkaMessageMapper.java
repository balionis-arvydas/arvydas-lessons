package com.balionis.arvydas.lesson8.consumer.stream.mappers;

import com.balionis.arvydas.lesson8.consumer.generated.model.Message;
import com.balionis.arvydas.lesson8.consumer.stream.model.KafkaMessage;
import org.mapstruct.Mapper;

@Mapper(implementationPackage = "com.balionis.arvydas.lesson8.consumer.stream.mappers.impl")
public interface KafkaMessageMapper {
    Message apply(KafkaMessage message);
}
