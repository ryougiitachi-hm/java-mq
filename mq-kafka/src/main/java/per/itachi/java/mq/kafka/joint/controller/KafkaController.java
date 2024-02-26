package per.itachi.java.mq.kafka.joint.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.itachi.java.mq.kafka.app.port.mq.KafkaProducerPort;

/**
 * To simplify the demo writing, joint layer may invoke the infra layer.
 * */
@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    private KafkaProducerPort kafkaProducerPort;

    @PostMapping("/event-messages/{topicName}")
    public void sendEventMessage(
            @PathVariable(name = "topicName", required = false) String topicName,
            @RequestBody String messageBody) {
        String strEventMessage = String.valueOf(System.currentTimeMillis());
        if (StringUtils.hasText(topicName)) {
            kafkaProducerPort.sendEventMessage(strEventMessage, topicName);
        }
        else {
            kafkaProducerPort.sendEventMessage(strEventMessage);
        }
    }

}
