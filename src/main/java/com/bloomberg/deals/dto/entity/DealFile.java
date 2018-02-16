/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bloomberg.deals.dto.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
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
@Table(name = "deal_file")
@NamedQueries({
    @NamedQuery(name = "DealFile.findAll", query = "SELECT d FROM DealFile d")})
public class DealFile implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "file_name")
    private String fileName;
    @Size(min = 1, max = 512)
    @Column(name = "file_path")
    private String filePath;
    @Basic(optional = false)
    @Column(name = "time_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeCreated;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "dealFile")
    private InvalidDealFile invalidDealFile;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "dealFile")
    private ValidDealFile validDealFile;

    public DealFile() {
    }

    public DealFile(String fileName, InvalidDealFile invalidDealFile, ValidDealFile validDealFile) {
        this.fileName = fileName;
        this.invalidDealFile = invalidDealFile;
        this.validDealFile = validDealFile;
    }
    
    

    public DealFile(Integer id) {
        this.id = id;
    }

    public DealFile(Integer id, String fileName, Date timeCreated) {
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

    public InvalidDealFile getInvalidDealFile() {
        return invalidDealFile;
    }

    public void setInvalidDealFile(InvalidDealFile invalidDealFile) {
        this.invalidDealFile = invalidDealFile;
    }

    public ValidDealFile getValidDealFile() {
        return validDealFile;
    }

    public void setValidDealFile(ValidDealFile validDealFile) {
        this.validDealFile = validDealFile;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
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
        if (!(object instanceof DealFile)) {
            return false;
        }
        DealFile other = (DealFile) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bloomberg.deals.dto.entity.DealFile[ id=" + id + " ]";
    }
    
}
