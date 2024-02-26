package per.itachi.java.mq.kafka.app.service.impl;

import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import per.itachi.java.mq.kafka.app.port.mq.KafkaProducerPort;
import per.itachi.java.mq.kafka.app.service.KafkaProducerService;

@Slf4j
@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {

    @Resource
    private KafkaProducerPort kafkaProducerPort;

    @Override
    public void produceMessage() {
        kafkaProducerPort.produceMessage();
        log.info("Generated a message via kafka producer. ");
    }
}
