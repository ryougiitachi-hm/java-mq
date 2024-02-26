package per.itachi.java.mq.kafka.joint.kafka;

import javax.annotation.PostConstruct;
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

    @PostConstruct
    public void init() {
        logger.info("Initialized. ");
    }

    @KafkaListener(
            containerFactory = "kafkaEventMessageListenerContainerFactory",
            topics = "#{kafkaConsumerProperties.kafkaConsumerTopicPropertiesMap['produceMessage'].topicName}",
            groupId = "#{kafkaConsumerProperties.kafkaConsumerTopicPropertiesMap['produceMessage'].consumerGroup}")
    public void consumeDefaultMessage(@Payload String messageBody) {
        logger.info("Received message from default topic, messageBody={}. ", messageBody);
    }

    @KafkaListener(
            containerFactory = "kafkaEventMessageListenerContainerFactory",
            topics = "#{kafkaConsumerProperties.eventMessageTopic}",
            groupId = "#{kafkaConsumerProperties.eventMessageConsumerGroup}")
    public void consumeEventMessageAsDefaultGrp(@Payload String messageBody) {
        logger.info("Received message from topic {}, messageBody={}. ", kafkaConsumerProperties.getEventMessageTopic(), messageBody);
    }
}
