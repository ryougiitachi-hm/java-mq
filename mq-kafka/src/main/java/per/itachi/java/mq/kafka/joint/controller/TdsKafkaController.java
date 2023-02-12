package per.itachi.java.mq.kafka.joint.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.itachi.java.mq.kafka.app.service.TdsKafkaService;

@RestController
@RequestMapping("/tds/kafka")
public class TdsKafkaController {

    @Autowired
    private TdsKafkaService tdsKafkaService;

    @PutMapping("/messages")
    public void putMessage() {
        tdsKafkaService.putMessage();
    }
}
