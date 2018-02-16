/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bloomberg.deals.dto;

import java.util.Date;

/**
 *
 * @author johnson3yo
 */
public class DealFileSearch {
    
    
    private String uploadFileName;
    private String validFilePath;
    private String invalidFilePath;
    private Date timeCreated;

    public DealFileSearch(String uploadFileName, String validFilePath, String invalidFilePath, Date timeCreated) {
        this.uploadFileName = uploadFileName;
        this.validFilePath = validFilePath;
        this.invalidFilePath = invalidFilePath;
        this.timeCreated = timeCreated;
    }
    
    

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String getValidFilePath() {
        return validFilePath;
    }

    public void setValidFilePath(String validFilePath) {
        this.validFilePath = validFilePath;
    }

    public String getInvalidFilePath() {
        return invalidFilePath;
    }

    public void setInvalidFilePath(String invalidFilePath) {
        this.invalidFilePath = invalidFilePath;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }
    
    
    
}
