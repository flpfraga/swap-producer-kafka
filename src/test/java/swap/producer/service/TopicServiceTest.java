package swap.producer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import swap.producer.controller.TopicControllerRequest;
import swap.producer.kafka.Producer;
import swap.producer.kafka.ProducerMessageDTO;
import swap.producer.mapper.GithubUserMapper;
import swap.producer.user.GithubUser;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Testes unitários para o serviço de tópicos.
 */
@ExtendWith(MockitoExtension.class)
public class TopicServiceTest {

    @Mock
    private Producer producer;

    @Mock
    private GithubUserMapper githubUserMapper;

    @InjectMocks
    private TopicService topicService;

    private TopicControllerRequest request;
    private GithubUser githubUser;

    @BeforeEach
    void setUp() {
        request = new TopicControllerRequest();
        request.setUser("testUser");
        request.setRepository("testRepo");
        request.setTopicName("test-topic");

        githubUser = GithubUser.newBuilder()
                .setUser("testUser")
                .setRepository("testRepo")
                .build();
    }

    /**
     * Testa se a mensagem é publicada corretamente no tópico.
     */
    @Test
    void testPublishMessageGithubTopic() {
        // Arrange
        when(githubUserMapper.toGithubUser(any(TopicControllerRequest.class))).thenReturn(githubUser);
        doNothing().when(producer).publish(any(ProducerMessageDTO.class));

        // Act
        topicService.publishMessageGithubTopic(request);

        // Assert
        verify(githubUserMapper, times(1)).toGithubUser(request);
        verify(producer, times(1)).publish(any(ProducerMessageDTO.class));
    }
} 