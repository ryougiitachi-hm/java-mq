package per.itachi.java.mq.kafka.infra.mq.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DefaultKafkaListenerErrorHandler implements KafkaListenerErrorHandler {

    @Override
    public Object handleError(Message<?> message, ListenerExecutionFailedException exception) {
        log.error("Error occurred when receiving kafka message. ", exception);
        return null; // currently don't know what should be returned.
    }
}
