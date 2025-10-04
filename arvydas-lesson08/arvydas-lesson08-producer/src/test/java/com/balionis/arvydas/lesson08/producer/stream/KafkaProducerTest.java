package com.balionis.arvydas.lesson08.producer.stream;

import com.balionis.arvydas.lesson08.producer.ApplicationException;
import com.balionis.arvydas.lesson08.producer.generated.model.SendMessageRequest;
import com.balionis.arvydas.lesson08.producer.stream.mappers.KafkaMessageMapper;
import com.balionis.arvydas.lesson08.producer.stream.model.KafkaMessage;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
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
    private KafkaTemplate<String, KafkaMessage> kafkaTemplate;

    @Mock
    private CompletableFuture<SendResult<String, KafkaMessage>> kafkaResult;

    private KafkaProducer kafkaProducer;

    private KafkaMessageMapper kafkaMessageMapper;

    @BeforeEach
    void setUp() {
        kafkaMessageMapper = Mappers.getMapper(KafkaMessageMapper.class);
        kafkaProducer = new KafkaProducer(kafkaTemplate, TEST_TOPIC, 1, kafkaMessageMapper);
    }

    @Test
    void should_send_message() {

        var request = createRequest();

        var message = kafkaMessageMapper.apply(request);

        var record = new ProducerRecord<>(TEST_TOPIC, message.getMessageId().toString(), message);

        when(kafkaTemplate.send(record)).thenReturn(kafkaResult);

        kafkaProducer.sendMessage(request);

        verify(kafkaTemplate).send(record);
    }

    @Test
    void should_throw_exception() throws InterruptedException, ExecutionException, TimeoutException {

        var request = createRequest();

        var message = kafkaMessageMapper.apply(request);

        var record = new ProducerRecord<>(TEST_TOPIC, message.getMessageId().toString(), message);

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
