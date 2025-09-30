package com.balionis.arvydas.lesson10.consumer.stream.serializers;

import com.balionis.arvydas.lesson10.avro.generated.v1.KafkaMessage;
import io.confluent.kafka.schemaregistry.avro.AvroSchema;
import io.confluent.kafka.schemaregistry.client.MockSchemaRegistryClient;
import io.confluent.kafka.schemaregistry.client.rest.exceptions.RestClientException;
import org.apache.avro.Schema;

import java.io.IOException;
import java.util.Arrays;

public class TestSchemaRegistryClient extends MockSchemaRegistryClient {

    private static final TestSchemaRegistryClient schemaRegistryClient = new TestSchemaRegistryClient();

    public static TestSchemaRegistryClient getInstance() {
        return schemaRegistryClient;
    }

    TestSchemaRegistryClient() {
        registerTopicSchema("my-messages", KafkaMessage.getClassSchema());
    }

    private void registerTopicSchema(String topic, Schema... schemas) {
        try {
            for (final var s : schemas) {
                register(topic + "-value", new AvroSchema(s));
            }
        } catch (IOException | RestClientException e) {
            throw new IllegalArgumentException("Failed to register schemas " + Arrays.toString(schemas) + " on topic " + topic, e);
        }
    }

}