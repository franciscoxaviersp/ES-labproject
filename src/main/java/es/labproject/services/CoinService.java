/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.labproject.services;

import es.labproject.models.Coin;
import es.labproject.repository.CoinRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Xico
 */
@Service
public class CoinService {
    
    private final CoinRepository coinRepository;

    @Autowired
    public CoinService(CoinRepository coinRepository) {
        this.coinRepository = coinRepository;
    }
    
    public List<Coin> getCoins(){
        return coinRepository.findAll();
    }
}
