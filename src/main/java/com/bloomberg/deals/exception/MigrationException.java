/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bloomberg.deals.exception;

/**
 *
 * @author johnson3yo
 */
public class MigrationException extends Exception {

    private String message;
    
    public MigrationException(String message){
        this.message = message;
    }
    
    @Override
    public synchronized Throwable getCause() {
        return super.getCause(); 
    }

   public String getMessage() {
        return message;
    }
    
    
    
}
