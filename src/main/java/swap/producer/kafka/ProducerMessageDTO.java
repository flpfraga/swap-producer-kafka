package swap.producer.kafka;

import org.apache.avro.generic.GenericRecord;

/**
 * DTO (Data Transfer Object) para transporte de informações de mensagens a serem publicadas no Kafka.
 * Encapsula chave, tópico e dados da mensagem em um único objeto.
 */
public class ProducerMessageDTO {
    private String messageKey;
    private String topic;
    private GenericRecord record;

    /**
     * Retorna a chave da mensagem.
     * 
     * @return A chave da mensagem
     */
    public String getMessageKey() {
        return messageKey;
    }

    /**
     * Define a chave da mensagem.
     * 
     * @param messageKey A chave da mensagem
     */
    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

    /**
     * Retorna o nome do tópico.
     * 
     * @return O nome do tópico
     */
    public String getTopic() {
        return topic;
    }

    /**
     * Define o nome do tópico.
     * 
     * @param topic O nome do tópico
     */
    public void setTopic(String topic) {
        this.topic = topic;
    }

    /**
     * Retorna o registro Avro.
     * 
     * @return O registro Avro
     */
    public GenericRecord getRecord() {
        return record;
    }

    /**
     * Define o registro Avro.
     * 
     * @param record O registro Avro
     */
    public void setRecord(GenericRecord record) {
        this.record = record;
    }

    /**
     * Construtor para o ProducerMessageDTO.
     * 
     * @param messageKey A chave da mensagem
     * @param topic O nome do tópico
     * @param record O registro Avro
     */
    public ProducerMessageDTO(String messageKey, String topic, GenericRecord record) {
        this.messageKey = messageKey;
        this.topic = topic;
        this.record = record;
    }
}
