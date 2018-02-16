/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bloomberg.deals.repository;

import com.bloomberg.deals.dto.DealCountDTO;
import com.bloomberg.deals.dto.entity.DealCount;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author johnson3yo
 */
@Repository
public interface DealCountRepository  extends JpaRepository<DealCount, Integer> {
    
    @Query(value="select new com.bloomberg.deals.dto.DealCountDTO(d.currencyIsoCode,"
            + "max(d.dealCount)) from DealCount d group by d.currencyIsoCode")
    List<DealCountDTO>getAccumulatedDealCount();
}
