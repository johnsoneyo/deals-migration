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
public class Row {

    public int data;
    public Row root;
    public String name;
    public Row next;

    public Row(int data, String name) {
        this.data = data;
        this.name = name;
        
    }
    
  

    public int getRowSize(Row list) {
        int count = 0;
        while (list != null) {
            ++count;
            list = list.next;
        }
        return count;
    }

}
