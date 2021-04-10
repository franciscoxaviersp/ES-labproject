/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.labproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Id;

/**
 *
 * @author Xico
 */
@Entity
@Table
public class Coin implements Comparable{
    
    @Id
    @SequenceGenerator(name="coin_sequence",sequenceName= "coin_sequence",allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="coin_sequence")
    @JsonIgnore
    private Long id;
    private String symbol;
    private double priceChange;
    private double priceChangePercent;
    private double weightedAvgPrice;
    private double prevClosePrice;
    private double lastPrice;
    private double lastQty;
    private double bidPrice;
    private double bidQty;
    private double askPrice;
    private double askQty;
    private double openPrice;
    private double highPrice;
    private double lowPrice;
    private double volume;
    private double quoteVolume;
    private Long openTime;
    private Long closeTime;
    private int firstId;
    private int lastId;
    private int count;

    public Coin() {
    }
    
    public Coin(String symbol) {
        this.symbol = symbol;
    }

    public Coin(Long id, String symbol, double priceChange, double priceChangePercent, double weightedAvgPercent, double prevClosePrice, double lastPrice, double lastQty, double bidPrice, double bidQty, double askPrice, double askQty, double openPrice, double highPrice, double lowPrice, double volume, double quoteVolume, Long openTime, Long closeTime, int firstId, int lastId, int count) {
        this.id = id;
        this.symbol = symbol;
        this.priceChange = priceChange;
        this.priceChangePercent = priceChangePercent;
        this.weightedAvgPrice = weightedAvgPercent;
        this.prevClosePrice = prevClosePrice;
        this.lastPrice = lastPrice;
        this.lastQty = lastQty;
        this.bidPrice = bidPrice;
        this.bidQty = bidQty;
        this.askPrice = askPrice;
        this.askQty = askQty;
        this.openPrice = openPrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.volume = volume;
        this.quoteVolume = quoteVolume;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.firstId = firstId;
        this.lastId = lastId;
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPriceChange() {
        return priceChange;
    }

    public void setPriceChange(double priceChange) {
        this.priceChange = priceChange;
    }

    public double getPriceChangePercent() {
        return priceChangePercent;
    }

    public void setPriceChangePercent(double priceChangePercent) {
        this.priceChangePercent = priceChangePercent;
    }

    public double getWeightedAvgPercent() {
        return weightedAvgPrice;
    }

    public void setWeightedAvgPercent(double weightedAvgPercent) {
        this.weightedAvgPrice = weightedAvgPercent;
    }

    public double getPrevClosePrice() {
        return prevClosePrice;
    }

    public void setPrevClosePrice(double prevClosePrice) {
        this.prevClosePrice = prevClosePrice;
    }

    public double getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(double lastPrice) {
        this.lastPrice = lastPrice;
    }

    public double getLastQty() {
        return lastQty;
    }

    public void setLastQty(double lastQty) {
        this.lastQty = lastQty;
    }

    public double getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(double bidPrice) {
        this.bidPrice = bidPrice;
    }

    public double getBidQty() {
        return bidQty;
    }

    public void setBidQty(double bidQty) {
        this.bidQty = bidQty;
    }

    public double getAskPrice() {
        return askPrice;
    }

    public void setAskPrice(double askPrice) {
        this.askPrice = askPrice;
    }

    public double getAskQty() {
        return askQty;
    }

    public void setAskQty(double askQty) {
        this.askQty = askQty;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(double openPrice) {
        this.openPrice = openPrice;
    }

    public double getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(double highPrice) {
        this.highPrice = highPrice;
    }

    public double getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(double lowPrice) {
        this.lowPrice = lowPrice;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getQuoteVolume() {
        return quoteVolume;
    }

    public void setQuoteVolume(double quoteVolume) {
        this.quoteVolume = quoteVolume;
    }

    public Long getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Long openTime) {
        this.openTime = openTime;
    }

    public Long getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Long closeTime) {
        this.closeTime = closeTime;
    }

    public int getFirstId() {
        return firstId;
    }

    public void setFirstId(int firstId) {
        this.firstId = firstId;
    }

    public int getLastId() {
        return lastId;
    }

    public void setLastId(int lastId) {
        this.lastId = lastId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.symbol);
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.priceChange) ^ (Double.doubleToLongBits(this.priceChange) >>> 32));
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.priceChangePercent) ^ (Double.doubleToLongBits(this.priceChangePercent) >>> 32));
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.weightedAvgPrice) ^ (Double.doubleToLongBits(this.weightedAvgPrice) >>> 32));
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.prevClosePrice) ^ (Double.doubleToLongBits(this.prevClosePrice) >>> 32));
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.lastPrice) ^ (Double.doubleToLongBits(this.lastPrice) >>> 32));
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.lastQty) ^ (Double.doubleToLongBits(this.lastQty) >>> 32));
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.bidPrice) ^ (Double.doubleToLongBits(this.bidPrice) >>> 32));
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.bidQty) ^ (Double.doubleToLongBits(this.bidQty) >>> 32));
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.askPrice) ^ (Double.doubleToLongBits(this.askPrice) >>> 32));
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.askQty) ^ (Double.doubleToLongBits(this.askQty) >>> 32));
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.openPrice) ^ (Double.doubleToLongBits(this.openPrice) >>> 32));
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.highPrice) ^ (Double.doubleToLongBits(this.highPrice) >>> 32));
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.lowPrice) ^ (Double.doubleToLongBits(this.lowPrice) >>> 32));
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.volume) ^ (Double.doubleToLongBits(this.volume) >>> 32));
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.quoteVolume) ^ (Double.doubleToLongBits(this.quoteVolume) >>> 32));
        hash = 23 * hash + Objects.hashCode(this.openTime);
        hash = 23 * hash + Objects.hashCode(this.closeTime);
        hash = 23 * hash + this.firstId;
        hash = 23 * hash + this.lastId;
        hash = 23 * hash + this.count;
        return hash;
    }

    

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Coin other = (Coin) obj;
        if (Double.doubleToLongBits(this.priceChange) != Double.doubleToLongBits(other.priceChange)) {
            return false;
        }
        if (Double.doubleToLongBits(this.priceChangePercent) != Double.doubleToLongBits(other.priceChangePercent)) {
            return false;
        }
        if (Double.doubleToLongBits(this.weightedAvgPrice) != Double.doubleToLongBits(other.weightedAvgPrice)) {
            return false;
        }
        if (Double.doubleToLongBits(this.prevClosePrice) != Double.doubleToLongBits(other.prevClosePrice)) {
            return false;
        }
        if (Double.doubleToLongBits(this.lastPrice) != Double.doubleToLongBits(other.lastPrice)) {
            return false;
        }
        if (Double.doubleToLongBits(this.lastQty) != Double.doubleToLongBits(other.lastQty)) {
            return false;
        }
        if (Double.doubleToLongBits(this.bidPrice) != Double.doubleToLongBits(other.bidPrice)) {
            return false;
        }
        if (Double.doubleToLongBits(this.bidQty) != Double.doubleToLongBits(other.bidQty)) {
            return false;
        }
        if (Double.doubleToLongBits(this.askPrice) != Double.doubleToLongBits(other.askPrice)) {
            return false;
        }
        if (Double.doubleToLongBits(this.askQty) != Double.doubleToLongBits(other.askQty)) {
            return false;
        }
        if (Double.doubleToLongBits(this.openPrice) != Double.doubleToLongBits(other.openPrice)) {
            return false;
        }
        if (Double.doubleToLongBits(this.highPrice) != Double.doubleToLongBits(other.highPrice)) {
            return false;
        }
        if (Double.doubleToLongBits(this.lowPrice) != Double.doubleToLongBits(other.lowPrice)) {
            return false;
        }
        if (Double.doubleToLongBits(this.volume) != Double.doubleToLongBits(other.volume)) {
            return false;
        }
        if (Double.doubleToLongBits(this.quoteVolume) != Double.doubleToLongBits(other.quoteVolume)) {
            return false;
        }
        if (this.openTime != other.openTime) {
            return false;
        }
        if (this.closeTime != other.closeTime) {
            return false;
        }
        if (this.firstId != other.firstId) {
            return false;
        }
        if (this.lastId != other.lastId) {
            return false;
        }
        if (this.count != other.count) {
            return false;
        }
        if (!Objects.equals(this.symbol, other.symbol)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Coin{" + "id=" + id + ", symbol=" + symbol + ", priceChange=" + priceChange + ", priceChangePercent=" + priceChangePercent + ", weightedAvgPercent=" + weightedAvgPrice + ", prevClosePrice=" + prevClosePrice + ", lastPrice=" + lastPrice + ", lastQty=" + lastQty + ", bidPrice=" + bidPrice + ", bidQty=" + bidQty + ", askPrice=" + askPrice + ", askQty=" + askQty + ", openPrice=" + openPrice + ", highPrice=" + highPrice + ", lowPrice=" + lowPrice + ", volume=" + volume + ", quoteVolume=" + quoteVolume + ", openTime=" + openTime + ", closeTime=" + closeTime + ", firstId=" + firstId + ", lastId=" + lastId + ", count=" + count + '}';
    }

    @Override
    public int compareTo(Object o) {
        double vol = ((Coin)o).getVolume();
        
        double diff = vol-this.volume;
        
        if(diff > 0){
            return (int)Math.ceil(diff);
        }else if(diff < 0){
            return (int)Math.floor(diff);
        }
        return (int)diff;
    }

}
