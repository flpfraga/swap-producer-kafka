# PUBLISHER.KAFKA #

### Recebe dados de user e repository de outros sistemas e publica via kafka ###

### Requisitos ###
Java 17

### Requisitos de teste ###
Docker

### Para executar a aplicação ###
[Execute os containers de acordo da aplicação orchestrador](https://github.com/flpfraga/swap-orchestrador/blob/master/README.md)

Inicie a aplicação.

Inicie a aplicação que busca dados de outras APIs
[orchestrador](https://github.com/flpfraga/swap-orchestrador)

Inicie a aplicação que consome dados do tópico INFO-REPOSITORY-GITHUB-TOPIC e publica via Webhooks
[publisher.webhook](https://github.com/flpfraga/swap-webhook-publishing)

Arquitetura da solução

```mermaid
flowchart TD
    subgraph Kafka
        A[PUBLISHER.KAFKA] --> B[USER-REPOSITORY-GITHUB-TOPIC]
    end

    B --> C[ORCHESTRADOR]
    C --> D[MONGODB]
    C --> E[INFO-REPOSITORY-GITHUB-TOPIC]
    
    subgraph Webhook
        E --> F[PUBLISHER.WEBHOOK]
        F --> G[WEBHOOK-API]
    end
```


