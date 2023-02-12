package per.itachi.java.mq.kafka.infra.mq.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KafkaInfraProperties {

    private String bootstrapServers;

    private String eventMessageTopic;

    private int timeout; // placeholder
}

