package per.itachi.java.mq.kafka.joint.kafka.interceptor;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

@Slf4j
public class Order1ConsumerInterceptor implements ConsumerInterceptor<String, String> {

    @Override
    public void configure(Map<String, ?> configs) {
        log.info("The consumer interceptor {} has the following configs, configs={}", getClass().getSimpleName(), configs);
    }

    @Override
    public ConsumerRecords<String, String> onConsume(ConsumerRecords<String, String> records) {
        log.info("The consumer interceptor {} received message, this={}. ", getClass().getSimpleName(), this);
        return records;
    }

    @Override
    public void onCommit(Map<TopicPartition, OffsetAndMetadata> offsets) {
        log.info("The consumer interceptor {} committed message offset, this={}, offsets={}. ", getClass().getSimpleName(), this, offsets);
    }

    @Override
    public void close() {
        log.info("The consumer interceptor {} is closed", getClass().getSimpleName());
    }
}