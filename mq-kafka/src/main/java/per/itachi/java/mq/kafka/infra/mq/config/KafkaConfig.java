package per.itachi.java.mq.kafka.infra.mq.config;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import per.itachi.java.mq.kafka.joint.kafka.KafkaConsumerProperties;

@Configuration
//@EnableKafka
public class KafkaConfig {

    @Bean
    @ConfigurationProperties("infra.kafka.svc")
    public KafkaInfraProperties kafkaInfraProperties() {
        return new KafkaInfraProperties();
    }

    // producer
    @Bean
    public ProducerFactory<String, String> kafkaProducerFactor(KafkaInfraProperties kafkaInfraProperties) {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaInfraProperties.getBootstrapServers());
//        props.put(ProducerConfig., StringSerializer.class);
        // need to notice that StringSerializer doesn't come from fastxml
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, kafkaInfraProperties.getKeySerializer());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, kafkaInfraProperties.getValueSerializer());
        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaProducerTemplate(ProducerFactory<String, String> kafkaProducerFactor) {
        return new KafkaTemplate<>(kafkaProducerFactor);
    }

    @Bean
    @ConfigurationProperties("infra.kafka.svc")
    public KafkaConsumerProperties svcKafkaConsumerProperties() {
        return new KafkaConsumerProperties();
    }

//    @Bean
    public ConsumerFactory<String, String> svcKafkaConsumerFactory() {
        KafkaConsumerProperties properties = svcKafkaConsumerProperties();
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
        config.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, properties.getAutoCommitIntervalMs());
        config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, properties.getEnableAutoCommit());
//        config.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, properties.getSessionTimeoutMs());
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, properties.getKeyDeserializer());
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, properties.getValueDeserializer());
        config.put(ConsumerConfig.GROUP_ID_CONFIG, properties.getGroupId());
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, properties.getAutoOffsetReset());
        return new DefaultKafkaConsumerFactory<>(config);
    }

//    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> svcConcurrentMessageListenerContainer() {
        ConsumerFactory<String, String> consumerFactory = svcKafkaConsumerFactory();
        ConcurrentKafkaListenerContainerFactory<String, String> listenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
        listenerContainerFactory.setConsumerFactory(consumerFactory);
//        listenerContainerFactory.setConcurrency(5);
//        listenerContainerFactory.setBatchListener(true);
//        listenerContainerFactory.getContainerProperties().setPollTimeout(1500);
//        listenerContainerFactory.getContainerProperties().setAckMode();
//        listenerContainerFactory.setAutoStartup(false);
        // RecordFilterStrategy
//        listenerContainerFactory.setAckDiscarded(true);
//        listenerContainerFactory.setRecordFilterStrategy(null);
        return listenerContainerFactory;
    }

    // KafkaMessageListenerContainer and MessageListener can replace KafkaListener.
}
