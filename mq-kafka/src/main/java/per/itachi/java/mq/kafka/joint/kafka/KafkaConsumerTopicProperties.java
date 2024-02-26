package per.itachi.java.mq.kafka.joint.kafka;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KafkaConsumerTopicProperties {

    private String topicName;

    private String consumerGroup;
}
