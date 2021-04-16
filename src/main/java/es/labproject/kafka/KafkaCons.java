
package es.labproject.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 *
 * @author Xico
 */
@Service
public class KafkaCons {

    private static final Logger logger = LoggerFactory.getLogger(KafkaCons.class);
    
    @KafkaListener(topics = "logs",groupId = "labproject")
    public void consumeLog(String message) throws IOException{
        logger.info(String.format("#### -> Consumed message -> %s", message));
    }

    @KafkaListener(topics = "data",groupId = "labproject")
    public void consumeData(String message) throws IOException{

        logger.info(String.format("#### -> Consumed message -> %s", message));
    }
}
