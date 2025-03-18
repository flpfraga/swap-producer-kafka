package swap.producer.config;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Configuração do produtor Kafka.
 * Define as propriedades de conexão e serialização para publicação de mensagens.
 */
@Configuration
public class KafkaProducerConfig {

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;
    
    @Value("${kafka.schema-registry-url}")
    private String schemaRegistryUrl;

    /**
     * Configura a fábrica de produtores Kafka com as propriedades necessárias.
     * 
     * @return Fábrica de produtores configurada
     */
    @Bean
    public ProducerFactory<String, SpecificRecordBase> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        configProps.put("schema.registry.url", schemaRegistryUrl);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    /**
     * Cria um template Kafka para facilitar o envio de mensagens.
     * 
     * @return Template Kafka configurado
     */
    @Bean
    public KafkaTemplate<String, SpecificRecordBase> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
