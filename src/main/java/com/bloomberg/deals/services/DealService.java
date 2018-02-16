/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bloomberg.deals.services;

import com.bloomberg.deals.dto.entity.Deal;
import com.bloomberg.deals.repository.DealRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author johnson3yo
 */
@Service
public class DealService {

    @Autowired
    private DealRepository drepo;

    public List<Deal> fetchDeals(String pageNo) {
        int res = (Integer.parseInt(pageNo) - 1);
        int offset = res != 0 ? res * (100) + 1 : 0;
        return drepo.fetchDeals(offset);
    }

}
