package com.balionis.arvydas.lesson10.consumer.stream.serializers;

import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;

import java.util.Map;

public class TestKafkaAvroDeserializer extends KafkaAvroDeserializer {
    public TestKafkaAvroDeserializer() {
        super(TestSchemaRegistryClient.getInstance());
    }

    public TestKafkaAvroDeserializer(SchemaRegistryClient schemaRegistryClient) {
        super(TestSchemaRegistryClient.getInstance());
    }

    public TestKafkaAvroDeserializer(SchemaRegistryClient schemaRegistryClient, Map<String, ?> props) {
        super(TestSchemaRegistryClient.getInstance(), props);
    }
}
