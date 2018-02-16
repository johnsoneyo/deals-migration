/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bloomberg.deals.services;

import com.bloomberg.deals.dto.DealFileSearch;
import com.bloomberg.deals.repository.DealFileRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author johnson3yo
 */
@Service
public class DealFileService {
    
    @Autowired private DealFileRepository drepo;
    
    public List<DealFileSearch>getFiles(String name){
       return drepo.findFileLikeName(name);       
    }
 
    
}
