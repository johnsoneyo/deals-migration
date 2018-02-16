/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bloomberg.deals.repository.dao;


import com.bloomberg.deals.dto.DealFileSearch;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author johnson3yo
 */
@Repository
public class DealFileDao {

    @PersistenceContext
    EntityManager em;
    
    @Transactional
    public void loadFile(String path){
     
     Query q = em.createNativeQuery("LOAD DATA LOCAL INFILE  :fileName \n"
                + "INTO TABLE deal \n"
                + "FIELDS TERMINATED BY ',' \n"
                + "ENCLOSED BY '\"'\n"
                + "LINES TERMINATED BY '\\n'\n"
                + "IGNORE 1 ROWS SET deal_time = CURDATE()").setParameter("fileName", path);
     q.executeUpdate();
    }
    


}
