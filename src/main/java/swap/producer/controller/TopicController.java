package swap.producer.controller;

import swap.producer.service.TopicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para gerenciamento de tópicos Kafka.
 * Expõe endpoints para publicação de mensagens nos tópicos configurados.
 */
@RestController
@RequestMapping(value = "publisher-topic")
public class TopicController {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    private final TopicService topicService;

    /**
     * Construtor para o controlador de tópicos.
     * 
     * @param topicService Serviço de tópicos utilizado para publicação de mensagens
     */
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    /**
     * Endpoint para adicionar uma mensagem a um tópico.
     * 
     * @param topicControllerRequest Objeto contendo os dados da mensagem
     * @return Confirmação da publicação da mensagem
     */
    @PostMapping("/add-msg")
    public String sendForTopic(@RequestBody TopicControllerRequest topicControllerRequest){
        LOG.info("Recebida requisição para publicar mensagem. Usuário: {}, Repositório: {}",
                topicControllerRequest.getUser(), topicControllerRequest.getRepository());
        topicService.publishMessageGithubTopic(topicControllerRequest);
        return "Mensagem publicada com sucesso!";
    }

}
