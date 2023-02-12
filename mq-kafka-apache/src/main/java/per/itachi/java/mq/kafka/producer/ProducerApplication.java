package per.itachi.java.mq.kafka.producer;

import java.io.IOException;
import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProducerApplication {

    private static final Logger logger = LoggerFactory.getLogger(ProducerApplication.class);

    public static void main(String[] args) throws IOException {
        // configuration
        Properties configs = new Properties();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9081");
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(configs);

        ProducerRecord<String, String> record = new ProducerRecord<String, String>("person-topic", String.valueOf(System.currentTimeMillis()));
        kafkaProducer.send(record, (metadata, e)->{
            if (e != null) {
                logger.error("", e);
            }
            else {
                logger.info("{}", metadata);
            }
        });

        kafkaProducer.close();
    }

}
