package com.balionis.arvydas.lesson8.consumer.stream;

import com.balionis.arvydas.lesson8.consumer.generated.model.Message;
import com.balionis.arvydas.lesson8.consumer.service.ConsumerService;
import com.balionis.arvydas.lesson8.consumer.stream.mappers.KafkaMessageMapper;
import com.balionis.arvydas.lesson8.consumer.stream.model.KafkaMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumer {

    @Autowired
    private final ConsumerService consumerService;

    @Autowired
    private final KafkaMessageMapper kafkaMessageMapper;

    @KafkaListener(topics = "${app.service.kafka.topic-name}")
    public void listen(ConsumerRecord<String, KafkaMessage> record) {
        log.info("record.key={}, record.value={}", record.key(), record.value());
        var message = kafkaMessageMapper.apply(record.value());
        consumerService.addMessage(message);
    }
}
