package swap.producer.config;

import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testes unitários para a configuração do produtor Kafka.
 */
@ExtendWith(MockitoExtension.class)
public class KafkaProducerConfigTest {

    @InjectMocks
    private KafkaProducerConfig kafkaProducerConfig;

    /**
     * Testa se a fábrica de produtores é configurada corretamente.
     */
    @Test
    void testProducerFactory() {
        // Arrange
        ReflectionTestUtils.setField(kafkaProducerConfig, "bootstrapServers", "localhost:9092");
        ReflectionTestUtils.setField(kafkaProducerConfig, "schemaRegistryUrl", "http://localhost:8081");

        // Act
        ProducerFactory<String, SpecificRecordBase> factory = kafkaProducerConfig.producerFactory();
        
        // Assert
        assertNotNull(factory);
        Map<String, Object> configs = factory.getConfigurationProperties();
        assertTrue(configs.containsKey(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG));
        assertTrue(configs.containsKey(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG));
        assertTrue(configs.containsKey(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG));
        assertTrue(configs.containsKey("schema.registry.url"));
    }

    /**
     * Testa se o template Kafka é criado corretamente.
     */
    @Test
    void testKafkaTemplate() {
        // Arrange
        ReflectionTestUtils.setField(kafkaProducerConfig, "bootstrapServers", "localhost:9092");
        ReflectionTestUtils.setField(kafkaProducerConfig, "schemaRegistryUrl", "http://localhost:8081");

        // Act
        KafkaTemplate<String, SpecificRecordBase> template = kafkaProducerConfig.kafkaTemplate();
        
        // Assert
        assertNotNull(template);
    }
} 