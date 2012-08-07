/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faks.kab.entities;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sale
 */
@Entity
@Table(name = "TAdresa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Adresa.findAll", query = "SELECT t FROM Adresa t"),
    @NamedQuery(name = "Adresa.findByAdresaID", query = "SELECT t FROM Adresa t WHERE t.adresaID = :adresaID"),
    @NamedQuery(name = "Adresa.findByNazivUlice", query = "SELECT t FROM Adresa t WHERE t.nazivUlice = :nazivUlice"),
    @NamedQuery(name = "Adresa.findByBroj", query = "SELECT t FROM Adresa t WHERE t.broj = :broj"),
    @NamedQuery(name = "Adresa.findByGradID", query = "SELECT t FROM Adresa t WHERE t.gradID = :gradID")})
public class Adresa implements Serializable, DomainEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "adresaID")
    private Integer adresaID;
    @Basic(optional = false)
    @Column(name = "naziv_ulice")
    private String nazivUlice;
    @Basic(optional = false)
    @Column(name = "broj")
    private int broj;
    @Basic(optional = false)
    @Column(name = "gradID")
    private int gradID;

    public Adresa() {
    }

    public Adresa(Integer adresaID) {
        this.adresaID = adresaID;
    }

    public Adresa(Integer adresaID, String nazivUlice, int broj, int gradID) {
        this.adresaID = adresaID;
        this.nazivUlice = nazivUlice;
        this.broj = broj;
        this.gradID = gradID;
    }

    public Integer getAdresaID() {
        return adresaID;
    }

    public void setAdresaID(Integer adresaID) {
        this.adresaID = adresaID;
    }

    public String getNazivUlice() {
        return nazivUlice;
    }

    public void setNazivUlice(String nazivUlice) {
        this.nazivUlice = nazivUlice;
    }

    public int getBroj() {
        return broj;
    }

    public void setBroj(int broj) {
        this.broj = broj;
    }

    public int getGradID() {
        return gradID;
    }

    public void setGradID(int gradID) {
        this.gradID = gradID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (adresaID != null ? adresaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Adresa)) {
            return false;
        }
        Adresa other = (Adresa) object;
        if ((this.adresaID == null && other.adresaID != null) || (this.adresaID != null && !this.adresaID.equals(other.adresaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.faks.com.faks.kab.components.Adresa[ adresaID=" + adresaID + " ]";
    }
    
}
