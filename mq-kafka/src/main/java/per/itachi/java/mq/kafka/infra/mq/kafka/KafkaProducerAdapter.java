package per.itachi.java.mq.kafka.infra.mq.kafka;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import per.itachi.java.mq.kafka.app.port.mq.KafkaProducerPort;
import per.itachi.java.mq.kafka.infra.mq.config.KafkaInfraProperties;

@Slf4j
@Component
public class KafkaProducerAdapter implements KafkaProducerPort {

    private static final String TOPIC_MQKAFKA_MESSAGE_IN = "mqkafka-message-in";

    private final Logger logger = LoggerFactory.getLogger(KafkaProducerAdapter.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaInfraProperties kafkaInfraProperties;

    @Override
    public void produceMessage() {
        logger.info("Sending message onto default topic {}. ", TOPIC_MQKAFKA_MESSAGE_IN);
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate
                .send(TOPIC_MQKAFKA_MESSAGE_IN, String.format("sendTimestamp=%d", System.currentTimeMillis()));
//        try {
//            SendResult<String, String> sendResult = future.get();
//            logger.info("Sent message onto default topic {}, future={}. ", TOPIC_MQKAFKA_MESSAGE_IN, future);
//        }
//        catch (InterruptedException | ExecutionException e) {
//            log.error("Error occurred when sending message. ", e);
//        }
        logger.info("Sent message onto default topic {}, future={}. ", TOPIC_MQKAFKA_MESSAGE_IN, future);
    }

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
