/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.labproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Xico
 */
@Entity
@Table
public class Candle {
    @Id
    @SequenceGenerator(name="candle_sequence",sequenceName= "candle_sequence",allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="candle_sequence")
    @JsonIgnore
    private Long id;
    private Date openTime;
    private double open;
    private double high;
    private double low;
    private double close;
    private double volume;
    private Date closeTime;   
    private double quoteAssetVolume;
    private int numberOfTrades;
    private double buyBaseAssetVolume;
    private double buyQuoteAssetVolume;
    private double ignore;

    public Candle() {
    }

    public Candle(Date openTime, double open, double high, double low, double close, double volume, Date closeTime, double quoteAssetVolume, int numberOfTrades, double buyBaseAssetVolume, double buyQuoteAssetVolume, double ignore) {
        this.openTime = openTime;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
        this.closeTime = closeTime;
        this.quoteAssetVolume = quoteAssetVolume;
        this.numberOfTrades = numberOfTrades;
        this.buyBaseAssetVolume = buyBaseAssetVolume;
        this.buyQuoteAssetVolume = buyQuoteAssetVolume;
        this.ignore = ignore;
    }
    
    public Candle(Long id, Date openTime, double open, double high, double low, double close, double volume, Date closeTime, double quoteAssetVolume, int numberOfTrades, double buyBaseAssetVolume, double buyQuoteAssetVolume, double ignore) {
        this.id = id;
        this.openTime = openTime;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
        this.closeTime = closeTime;
        this.quoteAssetVolume = quoteAssetVolume;
        this.numberOfTrades = numberOfTrades;
        this.buyBaseAssetVolume = buyBaseAssetVolume;
        this.buyQuoteAssetVolume = buyQuoteAssetVolume;
        this.ignore = ignore;
    }

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public double getQuoteAssetVolume() {
        return quoteAssetVolume;
    }

    public void setQuoteAssetVolume(double quoteAssetVolume) {
        this.quoteAssetVolume = quoteAssetVolume;
    }

    public int getNumberOfTrades() {
        return numberOfTrades;
    }

    public void setNumberOfTrades(int numberOfTrades) {
        this.numberOfTrades = numberOfTrades;
    }

    public double getBuyBaseAssetVolume() {
        return buyBaseAssetVolume;
    }

    public void setBuyBaseAssetVolume(double buyBaseAssetVolume) {
        this.buyBaseAssetVolume = buyBaseAssetVolume;
    }

    public double getBuyQuoteAssetVolume() {
        return buyQuoteAssetVolume;
    }

    public void setBuyQuoteAssetVolume(double buyQuoteAssetVolume) {
        this.buyQuoteAssetVolume = buyQuoteAssetVolume;
    }

    public double getIgnore() {
        return ignore;
    }

    public void setIgnore(double ignore) {
        this.ignore = ignore;
    }
    
    
}
