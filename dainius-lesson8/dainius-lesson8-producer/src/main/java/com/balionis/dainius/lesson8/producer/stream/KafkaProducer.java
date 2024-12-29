package com.balionis.dainius.lesson8.producer.stream;

import com.balionis.dainius.lesson8.producer.ApplicationException;
import com.balionis.dainius.lesson8.producer.generated.model.SendMessageRequest;
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

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final String topicName;

    private final int kafkaTimeoutSeconds;

    public void sendMessage(SendMessageRequest request) {
        try  {
            kafkaTemplate.send(createRecord(request)).get(kafkaTimeoutSeconds, TimeUnit.SECONDS);
        } catch (ExecutionException | TimeoutException | InterruptedException exc) {
            throw new ApplicationException("cannot send request="+request, exc);
        }
    }

    private ProducerRecord<String, String> createRecord(SendMessageRequest request) {
        return new ProducerRecord<>(topicName, request.getMessageId().toString(), request.getMessage());
    }
}