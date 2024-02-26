package per.itachi.java.mq.kafka.joint.kafka;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

@Configuration
public class KafkaConsumerConfig {

    @Bean
    @ConfigurationProperties("joint.kafka.svc")
    public KafkaConsumerProperties kafkaConsumerProperties() {
        return new KafkaConsumerProperties();
    }

    @Bean
    public ConsumerFactory<String, String> kafkaEventMessageConsumerFactory(
            KafkaConsumerProperties kafkaConsumerProperties) {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConsumerProperties.getBootstrapServers());
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, kafkaConsumerProperties.getKeyDeserializer());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, kafkaConsumerProperties.getValueDeserializer());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaConsumerProperties.getEventMessageConsumerGroup());
//        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaConsumerProperties.getEventMessageOffset());
        props.put(ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG, kafkaConsumerProperties.getInterceptorClassesConfig());
        props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, kafkaConsumerProperties.getSecurityProtocol());
        switch (kafkaConsumerProperties.getSecurityProtocol()) {
            case SSL:
                props.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, kafkaConsumerProperties.getSslTruststoreLocation());
                props.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, kafkaConsumerProperties.getSslTruststorePassword());
                props.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG, kafkaConsumerProperties.getSslKeystoreLocation());
                props.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG, kafkaConsumerProperties.getSslKeystorePassword());
                props.put(SslConfigs.SSL_KEY_PASSWORD_CONFIG, kafkaConsumerProperties.getSslKeyPassword());
                break;
            case SSL_PLAINTEXT:
                break;
            default:
                break;
        }
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaEventMessageListenerContainerFactory(
            ConsumerFactory<String, String> kafkaEventMessageConsumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(kafkaEventMessageConsumerFactory);
        return factory;
    }

}
