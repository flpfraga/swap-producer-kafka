package swap.producer.controller;

/**
 * Objeto de requisição para os endpoints do controlador de tópicos.
 * Contém os dados necessários para publicar uma mensagem no Kafka.
 */
public class TopicControllerRequest {
    private String topicName;
    private String user;
    private String repository;

    /**
     * Retorna o nome do tópico.
     * 
     * @return O nome do tópico
     */
    public String getTopicName() {
        return topicName;
    }

    /**
     * Define o nome do tópico.
     * 
     * @param topicName O nome do tópico
     */
    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    /**
     * Retorna o nome do usuário.
     * 
     * @return O nome do usuário
     */
    public String getUser() {
        return user;
    }

    /**
     * Define o nome do usuário.
     * 
     * @param user O nome do usuário
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Retorna o nome do repositório.
     * 
     * @return O nome do repositório
     */
    public String getRepository() {
        return repository;
    }

    /**
     * Define o nome do repositório.
     * 
     * @param repository O nome do repositório
     */
    public void setRepository(String repository) {
        this.repository = repository;
    }
}
