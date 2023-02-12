package per.itachi.java.mq.kafka.infra.mq.kafka;

/**
 * Should topicName be placed in front or behind?
 * */
public interface KafkaProducerPort {

    void sendEventMessage(String messageBody);

    void sendEventMessage(String messageBody, String topicName);
}
