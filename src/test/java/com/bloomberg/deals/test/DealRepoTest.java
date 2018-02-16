/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bloomberg.deals.test;


import com.bloomberg.deals.dto.DealFileSearch;
import com.bloomberg.deals.dto.entity.Deal;
import com.bloomberg.deals.repository.DealFileRepository;
import com.bloomberg.deals.repository.DealRepository;
import com.bloomberg.deals.repository.dao.DealFileDao;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
public class DealRepoTest {


    private static Logger logger = LogManager.getLogger(DealRepoTest.class);

    @Autowired
    private DealRepository repo;
    @Autowired
    private DealFileRepository rep;
    @Autowired
    private DealFileDao dod;

    //Test
    public void containsDeals() {

        List<Deal> all = repo.fetchDeals(1);
        Assert.assertNull(all);
    }

    @Test
    public void findByDealFileName() {
        List<DealFileSearch> fd = rep.findFileLikeName("fil%");
        DealFileSearch get = fd.get(0);
        Assert.assertNull(get);
    }

}
