/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bloomberg.deals.repository;

import com.bloomberg.deals.dto.entity.InvalidDealFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author johnson3yo
 */
@Repository
public interface InvalidFileRepository extends JpaRepository<InvalidDealFile, Integer> {
    
}
