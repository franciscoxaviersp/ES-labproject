package es.labproject.controllers;

import es.labproject.kafka.KafkaProd;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Autowired
    public KafkaController(KafkaProd producer) {
        this.producer = producer;
    }
    
    @PostMapping("/publish")
    public String messageToTopic(@RequestParam("message") String message){

        this.producer.send("data",message);
        this.producer.send("logs","Added data at "+dateFormat.format(new Date())+" : "+message);

        return "Published message: "+message;
    }
}