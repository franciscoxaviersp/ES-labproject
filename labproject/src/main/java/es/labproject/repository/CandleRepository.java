/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.labproject.repository;

import es.labproject.models.Candle;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Xico
 */
public interface CandleRepository extends JpaRepository<Candle,Long>{
    List<Candle> findByopenTime(Date openTime);
    List<Candle> findTop100ByOrderByOpenTimeDesc();
}
