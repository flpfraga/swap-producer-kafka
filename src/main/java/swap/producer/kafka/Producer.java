package swap.producer.kafka;

import org.apache.avro.specific.SpecificRecordBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * Produtor Kafka responsável pelo envio de mensagens para os tópicos configurados.
 * Utiliza o KafkaTemplate do Spring para simplificar o processo de publicação.
 */
@Service
public class Producer {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    private final KafkaTemplate<String, SpecificRecordBase> kafkaTemplate;

    /**
     * Construtor da classe Producer.
     * 
     * @param kafkaTemplate Template Kafka para envio de mensagens
     */
    public Producer(KafkaTemplate<String, SpecificRecordBase> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Publica uma mensagem no tópico Kafka especificado.
     * 
     * @param producerMessageDTO DTO contendo as informações da mensagem a ser publicada
     */
    public void publish(ProducerMessageDTO producerMessageDTO) {
        LOG.info("Publicando mensagem para tópico: {}", producerMessageDTO.getTopic());
        kafkaTemplate.send(MessageBuilder
                .withPayload(producerMessageDTO.getRecord())
                .setHeader(KafkaHeaders.TOPIC, producerMessageDTO.getTopic())
                .setHeader("kafka_messageKey", producerMessageDTO.getMessageKey())
                .build());
        LOG.debug("Mensagem enviada para processamento no Kafka");
    }

}
