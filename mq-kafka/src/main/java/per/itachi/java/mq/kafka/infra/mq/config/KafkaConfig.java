package per.itachi.java.mq.kafka.infra.mq.config;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaConfig {

    @Bean
    @ConfigurationProperties("infra.mq.kafka")
    public KafkaInfraProperties kafkaInfraProperties() {
        return new KafkaInfraProperties();
    }

    @Bean
    public ProducerFactory<String, String> kafkaProducerFactor(KafkaInfraProperties kafkaInfraProperties) {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaInfraProperties.getBootstrapServers());
        // need to notice that StringSerializer doesn't come from fastxml
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaProducerTemplate(ProducerFactory<String, String> kafkaProducerFactor) {
        return new KafkaTemplate<>(kafkaProducerFactor);
    }
}
