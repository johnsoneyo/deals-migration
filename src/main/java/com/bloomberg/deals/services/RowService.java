/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bloomberg.deals.services;

import com.bloomberg.deals.MainApp;
import com.bloomberg.deals.dto.Columns;
import com.bloomberg.deals.dto.Row;
import javax.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author johnson3yo
 */
@Service
public class RowService {

    private static Logger logger = LogManager.getLogger(MainApp.class);
    private Row structure;

    @PostConstruct
    public void initRowStructure() {

        String[] columns = Columns.values;
        structure = new Row(1, "deal_id");
        Row p = structure;
        for (int i = 1; i < columns.length; i++) {
            p = p.next = new Row(i + 1, columns[i]);
        }
        for (p = structure; p != null; p = p.next) {
            logger.debug(p.name);
        }

    }

    public Row getStructure() {
        return structure;
    }

    public void setStructure(Row structure) {
        this.structure = structure;
    }
    
    

}
