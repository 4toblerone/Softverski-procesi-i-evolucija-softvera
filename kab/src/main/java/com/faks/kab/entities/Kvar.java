package com.faks.kab.entities;
/*
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.faks.com.faks.kab.primarykeys.KvarCompositePK;
/*
@Entity
@Table(name = "TKvar")
@IdClass(KvarCompositePK.class)
public class Kvar implements Serializable, DomainEntity {

	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

/*
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name = "korisnikID", insertable=false,updatable=false)
	private Osoba osoba;

	@Id
	@Basic
	@Column(name = "korisnikID")
	private String korisnikID;

	@Id
	@Basic
	@Column(name = "vremePocetkaKvara")
	private Timestamp vreme_pocetka_kvara;

	@Basic
	@Column(name = "tipKvara")
	private String tip_kvara;

	@Basic
	@Column(name = "vremePrestankaKvara", nullable=true)
	private Date vreme_prestanka_kvara;

	@Basic
	@Column(name = "kvarResen")
	private boolean kvar_resen;

	public Kvar() {

	}

	public Kvar(String tip_kvara, Osoba osoba, Timestamp vreme_pocetka_kvara) {
		this.setTip_kvara(tip_kvara);
		this.setKorisnikID(osoba.getJmbg());
		this.setOsoba(osoba);
		this.setVreme_pocetka_kvara(vreme_pocetka_kvara);
		//this.setVreme_prestanka_kvara(null);//vreme_prestanka ne moze da bude null
		this.setKvar_resen(false);

	}

	public void setTip_kvara(String tip_kvara) {
		this.tip_kvara = tip_kvara;
	}

	public String getTip_kvara() {
		return tip_kvara;
	}

	public void setKorisnikID(String korisnikID) {
		this.korisnikID = korisnikID;
	}

	public String getKorisnikID() {
		return korisnikID;
	}

	public void setVreme_pocetka_kvara(Timestamp  vreme_pocetka_kvara) {
		this.vreme_pocetka_kvara = vreme_pocetka_kvara;
	}

	public Timestamp getVreme_pocetka_kvara() {
		return vreme_pocetka_kvara;
	}

	public void setVreme_prestanka_kvara(Date vreme_prestanka_kvara) {
		this.vreme_prestanka_kvara = vreme_prestanka_kvara;
	}

	public Date getVreme_prestanka_kvara() {
		return vreme_prestanka_kvara;
	}

	public void setKvar_resen(boolean kvar_resen) {
		this.kvar_resen = kvar_resen;
	}

	public boolean isKvar_resen() {
		return kvar_resen;
	}

	private void setOsoba(Osoba osoba) {
		this.osoba = osoba;
	}

	private Osoba getOsoba() {
		return osoba;
	}
	
	@Override
	public String toString() {
		
		return korisnikID;
	}

}
*/

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the TKvar database table.
 * 
 */
@Entity
@Table(name="TKvar")
public class Kvar implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private KvarPK id;

	private String tipKvara;

    //@Temporal( TemporalType.TIMESTAMP)
	private Timestamp vremePrestankaKvara;

	//bi-directional many-to-one association to TOsoba
    @ManyToOne
    @JoinColumn(name="korisnikID")
    @MapsId("korisnikID")
	private Osoba tosoba;

    
    
    
    
    public Kvar() {

    	
    }

    
    
	public KvarPK getId() {
		return this.id;
	}

	public void setId(KvarPK id) {
		this.id = id;
	}
	
	public String getTipKvara() {
		return this.tipKvara;
	}

	public void setTipKvara(String tipKvara) {
		this.tipKvara = tipKvara;
	}

	public Date getVremePrestankaKvara() {
		return this.vremePrestankaKvara;
	}

	public void setVremePrestankaKvara(Timestamp vremePrestankaKvara) {
		this.vremePrestankaKvara = vremePrestankaKvara;
	}

	public Osoba getTosoba() {
		return this.tosoba;
	}

	public void setTosoba(Osoba tosoba) {
		this.tosoba = tosoba;
	}
	
}













