package es.labproject.controllers;

import es.labproject.kafka.KafkaProd;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    private final KafkaProd producer;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private static final Logger log = LoggerFactory.getLogger(CoinController.class);

    @Autowired
    public KafkaController(KafkaProd producer) {
        this.producer = producer;
    }
    
    @PostMapping("/publish")
    public String messageToTopic(@RequestParam("message") String message){

        this.producer.send("data",message);
        log.info("Publishing data to topic data: "+message);

        return "Published message: "+message;
    }
}