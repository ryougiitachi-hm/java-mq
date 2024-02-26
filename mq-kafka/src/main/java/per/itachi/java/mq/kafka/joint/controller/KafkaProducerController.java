package per.itachi.java.mq.kafka.joint.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.itachi.java.mq.kafka.app.service.KafkaProducerService;

@RestController
@RequestMapping("/kafka/producer")
public class KafkaProducerController {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    /**
     * Produce a testing message to topic .
     * */
    @PostMapping("/produce-message")
    public void produceMessage() {
        kafkaProducerService.produceMessage();
    }
}