package com.balionis.arvydas.lesson11.consumer.stream.serializers;

import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient;
import io.confluent.kafka.serializers.KafkaAvroSerializer;

import java.util.Map;

public class TestKafkaAvroSerializer extends KafkaAvroSerializer {
    public TestKafkaAvroSerializer() {
        super(TestSchemaRegistryClient.getInstance());
    }

    public TestKafkaAvroSerializer(SchemaRegistryClient schemaRegistryClient) {
        super(TestSchemaRegistryClient.getInstance());
    }

    public TestKafkaAvroSerializer(SchemaRegistryClient schemaRegistryClient, Map<String, ?> props) {
        super(TestSchemaRegistryClient.getInstance(), props);
    }
}
