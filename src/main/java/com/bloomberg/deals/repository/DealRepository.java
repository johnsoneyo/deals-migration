/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bloomberg.deals.repository;

import com.bloomberg.deals.dto.entity.Deal;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author johnson3yo
 */
@Repository
public interface DealRepository extends CrudRepository<Deal, Integer> {


    @Query(value = "select * from deal order by deal_time desc limit ?1,100", nativeQuery = true)
    List<Deal> fetchDeals(int offset);


}
