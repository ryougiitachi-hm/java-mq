package per.itachi.java.mq.kafka.joint.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerListener {

    private final Logger logger = LoggerFactory.getLogger(KafkaConsumerListener.class);

    @Autowired
    private KafkaConsumerProperties kafkaConsumerProperties;

    @KafkaListener(
//            containerFactory = "kafkaEventMessageListenerContainerFactory",
            topics = "#{kafkaConsumerProperties.eventMessageTopic}",
            groupId = "#{kafkaConsumerProperties.eventMessageConsumerGroup}")
    public void consumeEventMessageAsDefaultGrp(@Payload String messageBody) {
        logger.info("Received message from topic {}, messageBody={}. ", kafkaConsumerProperties.getEventMessageTopic(), messageBody);
    }
}
