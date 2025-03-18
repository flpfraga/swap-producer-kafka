package swap.producer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import swap.producer.service.TopicService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Testes unitários para o controlador de tópicos.
 */
@ExtendWith(MockitoExtension.class)
public class TopicControllerTest {

    @Mock
    private TopicService topicService;

    @InjectMocks
    private TopicController topicController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(topicController).build();
        objectMapper = new ObjectMapper();
    }

    /**
     * Testa se o endpoint de envio de mensagem funciona corretamente.
     */
    @Test
    void testSendForTopic() throws Exception {
        // Arrange
        TopicControllerRequest request = new TopicControllerRequest();
        request.setUser("testUser");
        request.setRepository("testRepo");
        request.setTopicName("test-topic");

        doNothing().when(topicService).publishMessageGithubTopic(any(TopicControllerRequest.class));

        // Act & Assert
        mockMvc.perform(post("/publisher-topic/add-msg")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("Mensagem publicada com sucesso!"));

        verify(topicService).publishMessageGithubTopic(any(TopicControllerRequest.class));
    }
} 