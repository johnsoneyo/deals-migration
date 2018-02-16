/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bloomberg.deals.test;

import com.bloomberg.deals.dto.DealCountDTO;
import com.bloomberg.deals.repository.DealCountRepository;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author johnson3yo
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DealCountTest {
    
    @Autowired private DealCountRepository drepo;
    
    @Test
    public void dealCountTest(){
        List<DealCountDTO> accumulatedDealCount = drepo.getAccumulatedDealCount();
       Assert.assertEquals(12, accumulatedDealCount.size());
    }
    
}
