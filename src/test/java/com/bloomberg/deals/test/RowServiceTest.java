/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bloomberg.deals.test;

import com.bloomberg.deals.dto.Row;
import com.bloomberg.deals.exception.MigrationException;
import com.bloomberg.deals.services.CSVService;
import com.bloomberg.deals.services.RowService;
import java.io.IOException;
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
public class RowServiceTest {

    @Autowired
    private RowService service;
    @Autowired private CSVService csvService;
    private String values[] = new String[]{"deal_id",
        "from_currency_iso_code",
        "to_currency_iso_code",
        "deal_time",
        "amount"};

    //@Test
    public void validateColumnHeader() {
        Assert.assertTrue(notCompatible());
    }
    @Test
    public void processFile() throws IOException, MigrationException{        
        csvService.processFile(null);     
    }

    private boolean notCompatible() {

        Row root = service.getStructure();
        Row pointer = root;
        int c = 0;
        for (pointer = root; pointer != null; pointer = pointer.next) {
            if (c == values.length) {
                return false;
            }
            if (!pointer.name.equals(values[c])) {
                return false;
            }
            c++;
        }
        return true;
    }

}
