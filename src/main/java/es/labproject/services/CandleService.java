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
        return candleRepository.findAll();
    }
}
