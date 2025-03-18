package swap.producer.mapper;

import org.springframework.stereotype.Component;
import swap.producer.controller.TopicControllerRequest;
import swap.producer.user.GithubUser;

/**
 * Classe responsável pelo mapeamento de objetos de requisição para objetos Avro.
 * Separa a responsabilidade de criação de objetos Avro da camada de serviço.
 */
@Component
public class GithubUserMapper {

    /**
     * Converte um objeto TopicControllerRequest em um objeto GithubUser (Avro).
     * 
     * @param request O objeto de requisição contendo os dados do usuário e repositório
     * @return Um objeto GithubUser formatado conforme o esquema Avro
     */
    public GithubUser toGithubUser(TopicControllerRequest request) {
        return GithubUser.newBuilder()
                .setUser(request.getUser())
                .setRepository(request.getRepository())
                .build();
    }
} 