package per.itachi.java.mq.kafka.app.port.mq;

/**
 * Should topicName be placed in front or behind?
 * */
public interface KafkaProducerPort {

    /**
     * Generate testing message.
     * */
    void produceMessage();

    void sendEventMessage(String messageBody);

    void sendEventMessage(String messageBody, String topicName);
}
