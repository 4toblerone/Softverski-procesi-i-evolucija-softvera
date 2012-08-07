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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sale
 */
@Entity
@Table(name = "TGrad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = Grad.ALL, query = "SELECT t FROM Grad t"),
    @NamedQuery(name = Grad.BY_PTT, query = "SELECT t FROM Grad t WHERE t.ptt = :ptt"),
    @NamedQuery(name = Grad.BY_NAZIV_GRADA, query = "SELECT t FROM Grad t WHERE t.nazivGrada = :nazivGrada")})
public class Grad implements Serializable, DomainEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ptt")
    private Integer ptt;
    @Basic(optional = false)
    @Column(name = "naziv_grada")
    private String nazivGrada;
    
    public Grad() {
    }

    public Grad(Integer ptt) {
        this.ptt = ptt;
    }

    public Grad(Integer ptt, String nazivGrada) {
        this.ptt = ptt;
        this.nazivGrada = nazivGrada;
    }
    
    public static final String ALL ="Grad.findAll";
    
    public static final String BY_PTT = "Grad.findByPtt";
    
    public static final String BY_NAZIV_GRADA = "Grad.findByNazivGrada";

    public Integer getPtt() {
        return ptt;
    }

    public void setPtt(Integer ptt) {
        this.ptt = ptt;
    }

    public String getNazivGrada() {
        return nazivGrada;
    }

    public void setNazivGrada(String nazivGrada) {
        this.nazivGrada = nazivGrada;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ptt != null ? ptt.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grad)) {
            return false;
        }
        Grad other = (Grad) object;
        if ((this.ptt == null && other.ptt != null) || (this.ptt != null && !this.ptt.equals(other.ptt))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.faks.com.faks.kab.components.Grad[ ptt=" + ptt + " ]";
    }
    
}
