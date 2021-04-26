/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.labproject.controllers;


import es.labproject.models.Candle;
import es.labproject.models.Coin;
import es.labproject.repository.CandleRepository;
import es.labproject.repository.CoinRepository;
import es.labproject.services.CandleService;
import es.labproject.services.CoinService;
import es.labproject.kafka.KafkaProd;
import es.labproject.kafka.KafkaListner;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Xico
 */
@RestController
//@Controller
@RequestMapping("/api")
public class CoinController {
    
    private static final Logger log = LoggerFactory.getLogger(CoinController.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    
    @Autowired private CoinService coinService;
    @Autowired private CandleService candleService;
    @Autowired private CoinRepository coinRep;
    @Autowired private CandleRepository candleRep;
    @Autowired private KafkaProd producer;
    @Autowired private KafkaListner listner;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private List<Coin> pairCache; 
    
    @RequestMapping("/candles")
    public List<Candle> getCandles(){
        log.info("GET /api/candles");
        return candleService.getCandles();
    }
    
    @RequestMapping("/coins")
    public List getSymbolPrice(Model model){
        
        //model.addAttribute("coins",finalList);
        //return "coin";
        log.info("GET /api/coins");
        return pairCache;
    }
    @RequestMapping("/data")
    public String getMsg(){
        
        //model.addAttribute("coins",finalList);
        //return "coin";
        log.info("GET /api/data");
        return listner.getMessage();
    }
    
    
    @Scheduled(fixedRate=60000)
    public void updateCandles(){
        try{
            List<Candle> candles = getAPICandles();
            
            for(Candle candle: candles){
                List<Candle> toRemove = candleRep.findByopenTime(candle.getOpenTime());
                candleRep.deleteAll(toRemove);
                candleRep.saveAndFlush(candle);
            }
            
            log.info("Updated repository/database with Binance API info");
        }catch(Exception e){
            log.error("ERROR! Error updating repository/database with Binance API info");
            log.warn("No changes to database");
        }
    }
    
    @Scheduled(fixedRate = 60000)
    public void updatePairs(){
        log.info("Retrieving coin pair info from Binance API");
        String res = "";
        try{
            var url = new URL("https://api.binance.com/api/v3/ticker/24hr");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();

            
            if (responseCode == HttpURLConnection.HTTP_OK) {
                StringBuilder response;
                try (BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()))) {
                    response = new StringBuilder();
                    String readLine;
                    while ((readLine = in.readLine()) != null) {
                        response.append(readLine);
                    }
                }
                // print result
                res = response.toString();
                //GetAndPost.POSTRequest(response.toString());
            } else {
                log.error("Failed to connect with Binance API");
            }
            log.info("Successfully retrieved coin pairs info from Binance API");
        }catch(IOException e){
            log.error("ERROR! Error updating repository/database with Binance API info");
        }
        
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Coin[] coins = null;
                
        try {
            coins = objectMapper.readValue(res, Coin[].class);
            
        } catch (JsonProcessingException ex) {
            log.error("Error processing JSON to object");
        }
        List<Coin> coinList = new ArrayList(Arrays.asList(coins));
        List<Coin> finalList = new ArrayList();
        System.out.println(coinList.size());
        for(int i=0; i<coinList.size(); i++){
            Coin temp = coinList.get(i);
            
            if (temp.getSymbol().endsWith("EUR")){
                finalList.add(temp);
                if(temp.getPriceChangePercent()>=0.5) producer.send("data",temp.getSymbol()+","+String.valueOf(temp.getPriceChangePercent()));
            }
        }
        Collections.sort(finalList);
        
        pairCache = finalList;
    }
    
    public List getAPICandles(){
        
        log.info("Retrieving candle info from Binance API");
        String res = "";
        try{
            var url = new URL("https://api.binance.com/api/v3/klines?symbol=BTCEUR&interval=1m&limit=100");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();

            
            if (responseCode == HttpURLConnection.HTTP_OK) {
                StringBuilder response;
                try (BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()))) {
                    response = new StringBuilder();
                    String readLine;
                    while ((readLine = in.readLine()) != null) {
                        response.append(readLine);
                    }
                }
                // print result
                res = response.toString();
                //GetAndPost.POSTRequest(response.toString());
            } else {
                log.error("Failed to connect with Binance API");
            }
            log.info("Successfully retrieved candle info from Binance API");
        }catch(IOException e){
            log.error("ERROR! Error updating repository/database with Binance API info");
        }
        
        System.out.println(res);
        String[] items = res.substring(1,res.length()-1).replaceAll("\\[", "").split("\\]");
        List<Candle> candleList = new ArrayList<>();
        for(int i=0;i<items.length;i++ ){
            String temp;
            if (i!=0) temp = items[i].substring(1);
            else temp = items[i];
            String[] items2 = temp.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "").split(",");
            Date openTime = new Date(Long.parseLong(items2[0]));
            Date closeTime = new Date(Long.parseLong(items2[6]));
            Candle c = new Candle(openTime,Double.parseDouble(items2[1].replaceAll("\"","")),Double.parseDouble(items2[2].replaceAll("\"","")),Double.parseDouble(items2[3].replaceAll("\"","")),Double.parseDouble(items2[4].replaceAll("\"","")),Double.parseDouble(items2[5].replaceAll("\"","")),closeTime,Double.parseDouble(items2[7].replaceAll("\"","")),Integer.parseInt(items2[8]),Double.parseDouble(items2[9].replaceAll("\"","")),Double.parseDouble(items2[10].replaceAll("\"","")),Double.parseDouble(items2[11].replaceAll("\"","")));
            candleList.add(c);
        }
        return candleList;
    }
}
