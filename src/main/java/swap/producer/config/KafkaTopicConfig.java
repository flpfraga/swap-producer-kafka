package swap.producer.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

/**
 * Configuração de tópicos do Kafka.
 * Responsável por definir os tópicos e suas propriedades, garantindo que existam antes de serem utilizados.
 */
@Configuration
public class KafkaTopicConfig {

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;
    
    @Value("${kafka.topic.github.name}")
    private String githubTopicName;
    
    @Value("${kafka.topic.github.partitions:3}")
    private int githubTopicPartitions;
    
    @Value("${kafka.topic.github.replicas:1}")
    private short githubTopicReplicas;

    /**
     * Configura o admin do Kafka para gerenciamento de tópicos.
     * 
     * @return Configuração do KafkaAdmin
     */
    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        return new KafkaAdmin(configs);
    }

    /**
     * Define o tópico para mensagens do GitHub.
     * 
     * @return Configuração do tópico
     */
    @Bean
    public NewTopic githubTopic() {
        return TopicBuilder.name(githubTopicName)
                .partitions(githubTopicPartitions)
                .replicas(githubTopicReplicas)
                .build();
    }
} 