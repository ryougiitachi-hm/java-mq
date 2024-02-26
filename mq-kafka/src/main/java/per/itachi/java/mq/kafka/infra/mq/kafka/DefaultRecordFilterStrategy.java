package per.itachi.java.mq.kafka.infra.mq.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DefaultRecordFilterStrategy implements RecordFilterStrategy {

    @Override
    public boolean filter(ConsumerRecord consumerRecord) {
        log.info("The message will be filtered out, key={}", consumerRecord.key());
        return true; // the message will be filtered out when true.
    }
}
