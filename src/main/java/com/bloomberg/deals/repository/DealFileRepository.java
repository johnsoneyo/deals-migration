/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bloomberg.deals.repository;


import com.bloomberg.deals.dto.DealFileSearch;
import com.bloomberg.deals.dto.entity.DealFile;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author johnson3yo
 */
@Repository
public interface DealFileRepository extends JpaRepository<DealFile, Integer> {


    @Query(value= "select new com.bloomberg.deals.dto.DealFileSearch(d.fileName,"
            + "v.fileName ,i.fileName ,v.timeCreated) from DealFile d ,ValidDealFile v ,"
            + " InvalidDealFile i where v.id = d.id  and i.id = v.id and d.fileName like ?")
    List<DealFileSearch>findFileLikeName(String name);

    
}
