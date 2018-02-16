/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bloomberg.deals.repository.dao;

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
public class DealCountDao {
    
    @PersistenceContext private EntityManager em;
    
    @Transactional
    public void dealPerOrderingCurrency(){
        
        Query q = em.createNativeQuery(""
                + "insert into deal_count(currency_iso_code,deal_count) "
                + "select from_currency_iso_code,count(*) from deal group by "
                + "from_currency_iso_code ");
        q.executeUpdate();
    }
    
}
