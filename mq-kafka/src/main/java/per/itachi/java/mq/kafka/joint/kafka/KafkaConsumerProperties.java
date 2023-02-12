package per.itachi.java.mq.kafka.joint.kafka;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KafkaConsumerProperties {

    private String bootstrapServers;

    private String eventMessageTopic;

    private String eventMessageConsumerGroup;

    private String eventMessageOffset;
}
