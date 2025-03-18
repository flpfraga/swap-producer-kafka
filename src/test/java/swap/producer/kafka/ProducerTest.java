package swap.producer.kafka;

import org.apache.avro.specific.SpecificRecordBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import swap.producer.user.GithubUser;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Testes unitários para o produtor Kafka.
 */
@ExtendWith(MockitoExtension.class)
public class ProducerTest {

    @Mock
    private KafkaTemplate<String, SpecificRecordBase> kafkaTemplate;

    @InjectMocks
    private Producer producer;

    private ProducerMessageDTO messageDTO;
    private GithubUser githubUser;

    @BeforeEach
    void setUp() {
        githubUser = GithubUser.newBuilder()
                .setUser("testUser")
                .setRepository("testRepo")
                .build();

        messageDTO = new ProducerMessageDTO("test-key", "test-topic", githubUser);
    }

    /**
     * Testa se a mensagem é publicada corretamente no Kafka.
     */
    @Test
    void testPublish() {
        // Arrange
        when(kafkaTemplate.send(any(Message.class))).thenReturn(null);

        // Act
        producer.publish(messageDTO);

        // Assert
        verify(kafkaTemplate).send(any(Message.class));
    }
} 