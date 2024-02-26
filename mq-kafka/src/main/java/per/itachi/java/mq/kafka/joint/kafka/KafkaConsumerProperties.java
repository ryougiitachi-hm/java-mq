package per.itachi.java.mq.kafka.joint.kafka;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KafkaConsumerProperties {

    private String bootstrapServers;

    private Boolean enableAutoCommit;

    private Integer autoCommitIntervalMs;

    private Integer sessionTimeoutMs;

//    private Class<?> keyDeserializer;
//
//    private Class<?> valueDeserializer;

    private String keyDeserializer;

    private String valueDeserializer;

    private String groupId;

    private Boolean autoOffsetReset;

    private String eventMessageTopic;

    private String eventMessageConsumerGroup;

    private String eventMessageOffset;

    private List<String> interceptorClassesConfig;

    private KafkaSecurityProtocolTypeEnum securityProtocol;

    private String sslKeyPassword;

    private String sslTruststoreLocation;

    private String sslTruststorePassword;

    private String sslKeystoreLocation;

    private String sslKeystorePassword;

    private Map<String, KafkaConsumerTopicProperties> kafkaConsumerTopicPropertiesMap;
}
