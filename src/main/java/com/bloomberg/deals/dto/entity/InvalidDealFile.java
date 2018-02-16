/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bloomberg.deals.dto.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author johnson3yo
 */
@Entity
@Table(name = "invalid_deal_file")
@NamedQueries({
    @NamedQuery(name = "InvalidDealFile.findAll", query = "SELECT i FROM InvalidDealFile i")})
public class InvalidDealFile implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name="generator", strategy="foreign", parameters=@Parameter(value="dealFile", name = "property"))    
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "file_name")
    private String fileName;
    @Basic(optional = false)
    @Column(name = "time_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeCreated;
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    @PrimaryKeyJoinColumn
    private DealFile dealFile;

    public InvalidDealFile() {
    }

    public InvalidDealFile(Integer id) {
        this.id = id;
    }

    public InvalidDealFile(Integer id, String fileName, Date timeCreated) {
        this.id = id;
        this.fileName = fileName;
        this.timeCreated = timeCreated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    public DealFile getDealFile() {
        return dealFile;
    }

    public void setDealFile(DealFile dealFile) {
        this.dealFile = dealFile;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InvalidDealFile)) {
            return false;
        }
        InvalidDealFile other = (InvalidDealFile) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bloomberg.deals.dto.entity.InvalidDealFile[ id=" + id + " ]";
    }
    
}
