package per.itachi.java.mq.kafka.infra.mq.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
//@Component
public class KafkaSubscriptionListener {

    /**
     * Some of frequently used properties:
     * id
     * containerFactory
     * topics
     * topicPattern
     * errorHandler
     * groupId
     * */
    @KafkaListener(groupId = "svc-consumer-grp", topics = "svc-trade-updates")
    public void subscribeTradeUpdates(String message) {
        log.info("");
//        KafkaListenerEndpointRegistry
    }
}
