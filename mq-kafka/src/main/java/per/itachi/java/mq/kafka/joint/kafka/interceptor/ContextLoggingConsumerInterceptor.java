package per.itachi.java.mq.kafka.joint.kafka.interceptor;

import java.util.Map;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.MDC;

/**
 * Seems like one instance each consumer listener.
 * The order of executing the following methods:
 * <ul>
 * <li>configure - once. </li>
 * <li>onConsume/onCommit - each time when receiving message. </li>
 * <li>close - once. </li>
 * </ul>
 * When multiple interceptor, it would be
 * interceptor1.onConsume
 * interceptor2.onConsume
 * listener method.
 * interceptor1.onCommit
 * interceptor2.onCommit
 * */
@Slf4j
public class ContextLoggingConsumerInterceptor implements ConsumerInterceptor<String, String> {

    /**
     * Invoked once only when current interceptor is initialized.
     * */
    @Override
    public void configure(Map<String, ?> configs) {
    }

    @Override
    public ConsumerRecords<String, String> onConsume(ConsumerRecords<String, String> records) {
        MDC.put("x-request-id", UUID.randomUUID().toString());
        return records;
    }

    @Override
    public void onCommit(Map<TopicPartition, OffsetAndMetadata> offsets) {
        MDC.remove("x-request-id");
    }

    /**
     * Invoked once only when the current interceptor is closed instead of receiving message.
     * */
    @Override
    public void close() {
    }
}
