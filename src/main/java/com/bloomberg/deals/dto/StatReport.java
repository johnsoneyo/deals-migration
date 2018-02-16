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
public class StatReport {

    private Long processDuration;
    private int validRecordsCount;
    private int invalidRecordsCount;

    public StatReport(Long processDuration, int validRecordsCount, int invalidRecordsCount) {
        this.processDuration = processDuration;
        this.validRecordsCount = validRecordsCount;
        this.invalidRecordsCount = invalidRecordsCount;
    }

    public Long getProcessDuration() {
        return processDuration;
    }

    public void setProcessDuration(Long processDuration) {
        this.processDuration = processDuration;
    }

    public int getValidRecordsCount() {
        return validRecordsCount;
    }

    public void setValidRecordsCount(int validRecordsCount) {
        this.validRecordsCount = validRecordsCount;
    }

    public int getInvalidRecordsCount() {
        return invalidRecordsCount;
    }

    public void setInvalidRecordsCount(int invalidRecordsCount) {
        this.invalidRecordsCount = invalidRecordsCount;
    }
    
    
    

}
