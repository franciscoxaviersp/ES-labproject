package es.labproject.controllers;

import es.labproject.kafka.KafkaProd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    private final KafkaProd producer;

    @Autowired
    public KafkaController(KafkaProd producer) {
        this.producer = producer;
    }
    
    @PostMapping("/publish")
    public void messageToTopic(@RequestParam("message") String message){

        this.producer.send("data",message);
        this.producer.send("logs","Added: "message);

        return "Published message: "+message;
    }
}