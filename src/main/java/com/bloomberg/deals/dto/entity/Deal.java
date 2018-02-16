/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bloomberg.deals.dto.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author johnson3yo
 */
@Entity
@Table(name = "deal")
@NamedQueries({
    @NamedQuery(name = "Deal.findAll", query = "SELECT d FROM Deal d")})
public class Deal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "deal_id")
    private Integer dealId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "from_currency_iso_code")
    private String fromCurrencyIsoCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "to_currency_iso_code")
    private String toCurrencyIsoCode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "deal_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dealTime;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount")
    private BigDecimal amount;

    public Deal() {
    }

    public Deal(Integer dealId) {
        this.dealId = dealId;
    }

    public Deal(Integer dealId, String fromCurrencyIsoCode, String toCurrencyIsoCode, Date dealTime, BigDecimal amount) {
        this.dealId = dealId;
        this.fromCurrencyIsoCode = fromCurrencyIsoCode;
        this.toCurrencyIsoCode = toCurrencyIsoCode;
        this.dealTime = dealTime;
        this.amount = amount;
    }

    public Integer getDealId() {
        return dealId;
    }

    public void setDealId(Integer dealId) {
        this.dealId = dealId;
    }

    public String getFromCurrencyIsoCode() {
        return fromCurrencyIsoCode;
    }

    public void setFromCurrencyIsoCode(String fromCurrencyIsoCode) {
        this.fromCurrencyIsoCode = fromCurrencyIsoCode;
    }

    public String getToCurrencyIsoCode() {
        return toCurrencyIsoCode;
    }

    public void setToCurrencyIsoCode(String toCurrencyIsoCode) {
        this.toCurrencyIsoCode = toCurrencyIsoCode;
    }

    public Date getDealTime() {
        return dealTime;
    }

    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dealId != null ? dealId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Deal)) {
            return false;
        }
        Deal other = (Deal) object;
        if ((this.dealId == null && other.dealId != null) || (this.dealId != null && !this.dealId.equals(other.dealId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bloomberg.deals.dto.entity.Deal[ dealId=" + dealId + " ]";
    }
    
}
