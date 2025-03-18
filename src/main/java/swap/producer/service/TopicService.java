package swap.producer.service;

import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.beans.factory.annotation.Value;
import swap.producer.controller.TopicControllerRequest;
import swap.producer.kafka.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import swap.producer.kafka.ProducerMessageDTO;
import swap.producer.mapper.GithubUserMapper;
import swap.producer.user.GithubUser;

/**
 * Serviço responsável por orquestrar a publicação de mensagens em tópicos do Kafka.
 * Coordena o mapeamento de dados e chamada ao produtor.
 */
@Service
public class TopicService {

    private final String GITHUB_TOPIC_KEY = "github-topic";
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    private final Producer producer;
    private final GithubUserMapper githubUserMapper;

    /**
     * Construtor do serviço de tópicos.
     * 
     * @param producer O produtor Kafka utilizado para publicar mensagens
     * @param githubUserMapper O mapeador responsável por converter requests em objetos Avro
     */
    public TopicService(Producer producer, GithubUserMapper githubUserMapper) {
        this.producer = producer;
        this.githubUserMapper = githubUserMapper;
    }

    /**
     * Publica uma mensagem no tópico do GitHub.
     * 
     * @param topicControllerRequest Objeto contendo dados para publicação
     */
    public void publishMessageGithubTopic(TopicControllerRequest topicControllerRequest) {
        LOG.info("Iniciando publicação de mensagem para usuário: {} e repositório: {}", 
                topicControllerRequest.getUser(), 
                topicControllerRequest.getRepository());
                
        GithubUser githubUser = githubUserMapper.toGithubUser(topicControllerRequest);

        ProducerMessageDTO producerMessageDTO = new ProducerMessageDTO(
                GITHUB_TOPIC_KEY, topicControllerRequest.getTopicName(), githubUser);

        producer.publish(producerMessageDTO);
        
        LOG.info("Mensagem publicada com sucesso no tópico: {}", topicControllerRequest.getTopicName());
    }
}
