package per.itachi.java.mq.kafka.infra.mq.kafka;

import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@EmbeddedKafka(count = 5, ports = {9092, 9093, 9094, 9095, 9096})
public class KafkaSubscriptionListenerTest {

    @Test
    public void testKafka() throws IOException {
//        System.in.read();
    }
}
