/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faks.kab.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author sale
 */
@Entity
@Table(name = "TPaket")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = Paket.ALL, query = "SELECT t FROM Paket t"),
    @NamedQuery(name = Paket.BY_PAKET_ID, query = "SELECT t FROM Paket t WHERE t.paketID = :paketID"),
    @NamedQuery(name = "Paket.findByNazivPaketa", query = "SELECT t FROM Paket t WHERE t.nazivPaketa = :nazivPaketa"),
    @NamedQuery(name = "Paket.findByCenaPaketa", query = "SELECT t FROM Paket t WHERE t.cenaPaketa = :cenaPaketa"),
    @NamedQuery(name = "Paket.findByBrzina", query = "SELECT t FROM Paket t WHERE t.brzina = :brzina")})
public class Paket implements Serializable,DomainEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "paketID" ,  updatable = false)
    //@NonVisual
    private String paketID;
    @Basic(optional = false)
    @Column(name = "naziv_paketa" ,updatable=true, insertable  = true)
    private String nazivPaketa;
    @Basic(optional = false)
    @Column(name = "cena_paketa" , updatable =true , insertable = true)
    private int cenaPaketa;
    @Basic(optional = false)
    @Lob
    @Column(name = "opis_paketa", updatable = true , insertable = true)
    private String opisPaketa;
    @Basic(optional = false)
    @Column(name = "brzina" , updatable = true, insertable = true )
    private int brzina;

    @Inject
    public Paket() {
    }

    public Paket(String paketID) {
        this.paketID = paketID;
    }

   
    public Paket(String paketID, String nazivPaketa, int cenaPaketa, String opisPaketa, int brzina) {
        this.paketID = paketID;
        this.nazivPaketa = nazivPaketa;
        this.cenaPaketa = cenaPaketa;
        this.opisPaketa = opisPaketa;
        this.brzina = brzina;
    }

    public static final String ALL = "Paket.findAll";
    
    public static final String BY_PAKET_ID = "Paket.findByPaketID";
    
    public String getPaketID() {
        return paketID;
    }

    public void setPaketID(String paketID) {
        this.paketID = paketID;
    }

    public String getNazivPaketa() {
        return nazivPaketa;
    }

    public void setNazivPaketa(String nazivPaketa) {
        this.nazivPaketa = nazivPaketa;
    }

    public int getCenaPaketa() {
        return cenaPaketa;
    }

    public void setCenaPaketa(int cenaPaketa) {
        this.cenaPaketa = cenaPaketa;
    }

    public String getOpisPaketa() {
        return opisPaketa;
    }

    public void setOpisPaketa(String opisPaketa) {
        this.opisPaketa = opisPaketa;
    }

    public int getBrzina() {
        return brzina;
    }

    public void setBrzina(int brzina) {
        this.brzina = brzina;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paketID != null ? paketID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paket)) {
            return false;
        }
        Paket other = (Paket) object;
        if ((this.paketID == null && other.paketID != null) || (this.paketID != null && !this.paketID.equals(other.paketID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.faks.com.faks.kab.components.Paket[ paketID=" + paketID + " ]";
    }
    
}
