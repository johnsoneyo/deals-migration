/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bloomberg.deals.dto;

/**
 *
 * @author johnson3yo
 */
public class DealCountDTO {
    
    private String isoCurr;
    private int count;

    public DealCountDTO(String isoCurr, int count) {
        this.isoCurr = isoCurr;
        this.count = count;
    }

    public String getIsoCurr() {
        return isoCurr;
    }

    public void setIsoCurr(String isoCurr) {
        this.isoCurr = isoCurr;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    
    
}
