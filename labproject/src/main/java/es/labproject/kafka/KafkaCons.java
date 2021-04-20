
package es.labproject.kafka;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 *
 * @author Xico
 */
@Service
public class KafkaCons {
/*
    private static final Logger logger = LoggerFactory.getLogger(KafkaCons.class);
    
    private List<String> logs = new LinkedList<>();
    private List<String> data = new LinkedList<>();
    
    public List<String> getLogs() {
        return logs;
    }
    
    public List<String> getData() {
        return data;
    }
    
    @KafkaListener(topics = "logs",groupId = "labproject")
    public void consumeLog(String message) throws IOException{
        logs.add(message);
        System.out.println(message);
    }

    @KafkaListener(topics = "data",groupId = "labproject")
    public void consumeData(String message) throws IOException{

        logger.info(String.format("#### -> Consumed message -> %s", message));
        data.add(message);
    }*/
}
