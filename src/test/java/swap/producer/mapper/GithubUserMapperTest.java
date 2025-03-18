package swap.producer.mapper;

import org.junit.jupiter.api.Test;
import swap.producer.controller.TopicControllerRequest;
import swap.producer.user.GithubUser;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Testes unitários para o mapeador de usuários GitHub.
 */
public class GithubUserMapperTest {

    private GithubUserMapper mapper = new GithubUserMapper();

    /**
     * Testa se a conversão de TopicControllerRequest para GithubUser é feita corretamente.
     */
    @Test
    void testToGithubUser() {
        // Arrange
        TopicControllerRequest request = new TopicControllerRequest();
        request.setUser("testUser");
        request.setRepository("testRepo");
        request.setTopicName("test-topic");

        // Act
        GithubUser result = mapper.toGithubUser(request);

        // Assert
        assertEquals("testUser", result.getUser());
        assertEquals("testRepo", result.getRepository());
    }
} 