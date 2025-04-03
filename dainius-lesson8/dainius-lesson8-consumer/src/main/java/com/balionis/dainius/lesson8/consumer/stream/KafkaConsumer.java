package com.balionis.dainius.lesson8.consumer.stream;

import com.balionis.dainius.lesson8.consumer.generated.model.Message;
import com.balionis.dainius.lesson8.consumer.service.ConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class KafkaConsumer {

    @Autowired
    private ConsumerService consumerService;

    @KafkaListener(groupId = "${app.service.name}", topics = "${app.service.kafka.topic-name}")
    public void listen(ConsumerRecord<String, String> record) {
        log.info("record.key={}, record.value={}", record.key(), record.value());
        var message = new Message(UUID.fromString(record.key()), record.value());
        consumerService.addMessage(message);
    }
}
