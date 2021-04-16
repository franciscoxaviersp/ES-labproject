
package es.labproject.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 *
 * @author Xico
 */
@Service
public class KafkaCons {

    @KafkaListener(topics = "logs",groupId = "labproject")
    public void consumeMessage(String message) throws IOException{
        logger.info(String.format("#### -> Consumed message -> %s", message));
    }

    @KafkaListener(topics = "data",groupId = "labproject")
    public void consumeMessage(String message) throws IOException{

        logger.info(String.format("#### -> Consumed message -> %s", message));
    }
}
