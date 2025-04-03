package com.balionis.dainius.lesson9.producer.stream;

import com.balionis.dainius.lesson9.producer.ApplicationException;
import com.balionis.dainius.lesson9.producer.generated.model.SendMessageRequest;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class KafkaProducerTest {

    private static final String TEST_TOPIC = "test-topic";

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @Mock
    private CompletableFuture<SendResult<String, String>> kafkaResult;

    private KafkaProducer kafkaProducer;

    @BeforeEach
    void setUp() {
        kafkaProducer = new KafkaProducer(kafkaTemplate, TEST_TOPIC, 1);
    }

    @Test
    void should_send_message() {

        var request = createRequest();

        var record = new ProducerRecord<>(TEST_TOPIC, request.getMessageId().toString(), request.getMessage());

        when(kafkaTemplate.send(record)).thenReturn(kafkaResult);

        kafkaProducer.sendMessage(request);

        verify(kafkaTemplate).send(record);
    }

    @Test
    void should_throw_exception() throws InterruptedException, ExecutionException, TimeoutException {

        var request = createRequest();

        var record = new ProducerRecord<>(TEST_TOPIC, request.getMessageId().toString(), request.getMessage());

        doThrow(new TimeoutException("cannot send"))
                .when(kafkaResult).get(1, TimeUnit.SECONDS);

        when(kafkaTemplate.send(record)).thenReturn(kafkaResult);

        ApplicationException exception = assertThrows(ApplicationException.class, () -> kafkaProducer.sendMessage(request));
        assertThat(exception.getMessage()).startsWith("cannot send request");

        verify(kafkaTemplate).send(record);
    }

    private SendMessageRequest createRequest() {
        return new SendMessageRequest()
                .messageId(UUID.fromString(UUID.randomUUID().toString()))
                .message("test-message");
    }
}
