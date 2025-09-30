package com.balionis.arvydas.lesson8.producer.stream;

import com.balionis.arvydas.lesson8.producer.ApplicationException;
import com.balionis.arvydas.lesson8.producer.generated.model.SendMessageRequest;
import com.balionis.arvydas.lesson8.producer.stream.mappers.KafkaMessageMapper;
import com.balionis.arvydas.lesson8.producer.stream.model.KafkaMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, KafkaMessage> kafkaTemplate;

    private final String topicName;

    private final int kafkaTimeoutSeconds;

    private final KafkaMessageMapper kafkaMessageMapper;

    public void sendMessage(SendMessageRequest request) {
        try  {
            kafkaTemplate.send(createRecord(request)).get(kafkaTimeoutSeconds, TimeUnit.SECONDS);
            log.info("sent messageId={}", request.getMessageId());
        } catch (ExecutionException | TimeoutException | InterruptedException exc) {
            throw new ApplicationException("cannot send request="+request, exc);
        }
    }

    private ProducerRecord<String, KafkaMessage> createRecord(SendMessageRequest request) {
        var message = kafkaMessageMapper.apply(request);
        return new ProducerRecord<>(topicName, message.getMessageId().toString(), message);
    }
}