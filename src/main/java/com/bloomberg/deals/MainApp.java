/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bloomberg.deals;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author johnson3yo
 */
@SpringBootApplication
public class MainApp {
  
  private static Logger logger = LogManager.getLogger(MainApp.class);

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(MainApp.class);
    app.run(args);
  }
}