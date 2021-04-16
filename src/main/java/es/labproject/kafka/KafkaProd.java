/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.labproject.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author Xico
 */
@Service
public class KafkaProd {
    
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    public void send(String topic,String message){
        logger.info(String.format("#### -> Producing message -> %s", message));    
        this.kafkaTemplate.send(topic,message);
    }

//    @Bean
//    public NewTopic createTopic(){
//
//        return new NewTopic(TOPIC,1,(short) 1);
//    }


}
