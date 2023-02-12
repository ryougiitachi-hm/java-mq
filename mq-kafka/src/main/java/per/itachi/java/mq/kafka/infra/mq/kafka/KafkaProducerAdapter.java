package per.itachi.java.mq.kafka.infra.mq.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import per.itachi.java.mq.kafka.infra.mq.config.KafkaInfraProperties;

@Component
public class KafkaProducerAdapter implements KafkaProducerPort {

    private final Logger logger = LoggerFactory.getLogger(KafkaProducerAdapter.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaInfraProperties kafkaInfraProperties;

    @Override
    public void sendEventMessage(String messageBody) {
        sendEventMessage(messageBody, kafkaInfraProperties.getEventMessageTopic());
    }

    @Override
    public void sendEventMessage(String messageBody, String topicName) {
        logger.info("Sending event message onto topic {}. ", topicName);
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, messageBody);
        logger.info("Sent event message onto topic {}, future={}. ", topicName, future);
    }

}
