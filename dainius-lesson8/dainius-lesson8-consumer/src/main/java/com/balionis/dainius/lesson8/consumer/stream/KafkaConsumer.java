package com.balionis.dainius.lesson8.consumer.stream;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumer {

    @KafkaListener(groupId = "${app.service.name}", topics = "${app.service.kafka.topic-name}")
    public void listen(ConsumerRecord<String, String> record) {
        log.info("record.key={}, record.value={}", record.key(), record.value());
    }
}
