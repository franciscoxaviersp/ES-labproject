/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.labprojectclient;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Xico
 */
@RestController
public class ClientController {
    @Autowired
    private KafkaCons consumer;

    @RequestMapping("/test")
    public String test(){
        return "test";
    }

    @RequestMapping("/logs")
    public String test(){
        return consumer.getLogs();
    }

    @RequestMapping("/data")
    public String test(){
        return consumer.getData();
    }
}
