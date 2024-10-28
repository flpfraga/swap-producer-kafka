package swap.producer.service;

import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.beans.factory.annotation.Value;
import swap.producer.controller.TopicControllerRequest;
import swap.producer.kafka.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import swap.producer.kafka.ProducerMessageDTO;
import swap.producer.user.GithubUser;

@Service
public class TopicService {

    private final String GITHUB_TOPIC_KEY = "github-topic";
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    private final Producer producer;

    public TopicService(Producer producer) {
        this.producer = producer;
    }

    public void publishMessageGithubTopic(TopicControllerRequest topicControllerRequest) {

        GithubUser githubUser = GithubUser.newBuilder()
                .setUser(topicControllerRequest.getUser())
                .setRepository(topicControllerRequest.getRepository())
                .build();

        ProducerMessageDTO producerMessageDTO = new ProducerMessageDTO(
                GITHUB_TOPIC_KEY, topicControllerRequest.getTopicName(), githubUser);

        producer.publish(producerMessageDTO);
    }
}
