/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bloomberg.deals.dto.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author johnson3yo
 */
@Entity
@Table(name = "deal_count")
@NamedQueries({
    @NamedQuery(name = "DealCount.findAll", query = "SELECT d FROM DealCount d")})
public class DealCount implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "deal_count_id")
    private Integer dealCountId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "currency_iso_code")
    private String currencyIsoCode;
    @Column(name = "deal_count")
    private Integer dealCount;

    public DealCount() {
    }

    public DealCount(Integer dealCountId) {
        this.dealCountId = dealCountId;
    }

    public DealCount(Integer dealCountId, String currencyIsoCode) {
        this.dealCountId = dealCountId;
        this.currencyIsoCode = currencyIsoCode;
    }

    public Integer getDealCountId() {
        return dealCountId;
    }

    public void setDealCountId(Integer dealCountId) {
        this.dealCountId = dealCountId;
    }

    public String getCurrencyIsoCode() {
        return currencyIsoCode;
    }

    public void setCurrencyIsoCode(String currencyIsoCode) {
        this.currencyIsoCode = currencyIsoCode;
    }

    public Integer getDealCount() {
        return dealCount;
    }

    public void setDealCount(Integer dealCount) {
        this.dealCount = dealCount;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dealCountId != null ? dealCountId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DealCount)) {
            return false;
        }
        DealCount other = (DealCount) object;
        if ((this.dealCountId == null && other.dealCountId != null) || (this.dealCountId != null && !this.dealCountId.equals(other.dealCountId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bloomberg.deals.dto.entity.DealCount[ dealCountId=" + dealCountId + " ]";
    }
    
}
