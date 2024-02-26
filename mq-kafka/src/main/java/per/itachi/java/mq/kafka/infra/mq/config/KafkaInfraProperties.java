package per.itachi.java.mq.kafka.infra.mq.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KafkaInfraProperties {

    private String bootstrapServers;

    private String keySerializer;

    private String valueSerializer;

    private String eventMessageTopic;

    private int timeout; // placeholder
}
