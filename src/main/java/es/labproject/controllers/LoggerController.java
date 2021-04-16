/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.labproject.controllers;

import es.labproject.kafka.KafkaCons;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Xico
 */
@RestController
@RequestMapping("/logging")
public class LoggerController {
    private Logger logger = LoggerFactory.getLogger(LoggerController.class);

    @Autowired
    private KafkaCons consumer;

//    @GetMapping("/logs")
//    public List logging() {
//        logger.info("Consuming logging messages");
//
//        return consumer.getLogs();
//    }
//
//    @GetMapping("/data")
//    public List data() {
//        logger.info("Consuming data messages");
//
//        return consumer.getData();
//    }

}