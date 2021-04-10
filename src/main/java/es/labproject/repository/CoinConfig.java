/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.labproject.repository;

import es.labproject.models.Candle;
import es.labproject.models.Coin;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Xico
 */
@Configuration
public class CoinConfig {
    
    @Bean
    CommandLineRunner commandLineRunner(CoinRepository repository){
        return args ->{
            Coin eth = new Coin("ETHBTC");
            Coin ada = new Coin("ADABTC");
            repository.saveAll(List.of(eth,ada));
        };
    }
    
}
