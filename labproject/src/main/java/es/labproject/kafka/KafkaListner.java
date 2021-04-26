/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.labproject.kafka;

/**
 *
 * @author filipe
 */

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Queue;
import java.util.LinkedList;


@Service
public class KafkaListner {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProd.class);

    private String msg = "";
    private  Queue<String> logs = new LinkedList<>();

    @KafkaListener(topics = "data", groupId = "labproject")
    public void consumer1(ConsumerRecord<String, String> payload){
        logger.info("Topic: {}", "data");
        logger.info("Order: {}", payload.value());
        msg = payload.value();
    }
    
    @KafkaListener(topics = "logs", groupId = "labproject")
    public void consumer2(ConsumerRecord<String, String> payload){
        
        logs.add(payload.value());
        // Display contents of the queue.
        System.out.println("Elements of queue "
                           + logs);
    }

    public String getMessage(){
        return msg;
    }
}