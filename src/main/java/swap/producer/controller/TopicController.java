package swap.producer.controller;

import swap.producer.service.TopicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "publisber-topic")
public class TopicController {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }


    @PostMapping("/add-msg")
    public void sendForTopic(@RequestBody TopicControllerRequest topicControllerRequest){
        topicService.publishMessageGithubTopic(topicControllerRequest);
    }

}
