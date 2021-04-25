package es.labproject.services;

import es.labproject.models.Candle;
import es.labproject.repository.CandleRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Xico
 */
@Service
public class CandleService {

    private final CandleRepository candleRepository;

    @Autowired
    public CandleService(CandleRepository candleRepository) {
        this.candleRepository = candleRepository;
    }
    
    public List<Candle> getCandles(){
        //List<Candle> list=candleRepository.findAll();
        //return list.subList(list.size()-101,list.size()-1);
        return candleRepository.findTop100ByOrderByOpenTimeDesc();
    }
}
