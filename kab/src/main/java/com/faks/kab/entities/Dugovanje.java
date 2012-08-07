package com.faks.kab.entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the TDugovanje database table.
 * 
 */
@Entity
@Table(name="TDugovanje")
@NamedQueries({
		@NamedQuery(name = Dugovanje.ALL, query = "SELECT t FROM Dugovanje t")})
public class Dugovanje implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String ALL =  "Dugovanje.findAll";;
	
	@EmbeddedId
	private DugovanjePK id;
	
	private int iznos;

  /*  @Temporal( TemporalType.DATE)
	private Date mesec;*/

	//bi-directional many-to-one association to TOsoba
    @ManyToOne
	@JoinColumn(name="korisnikID")
	@MapsId("korisnikID")
	private Osoba osoba;

	//uni-directional one-to-one association to TPaket
	@OneToOne
	@JoinColumn(name="paketID")
	private Paket paket;

    public Dugovanje() {
    }
    
    public DugovanjePK getId(){
    	return id;
    }
    
    public void setId(DugovanjePK id){
    	this.id = id;
    }

	public int getIznos() {
		return this.iznos;
	}

	public void setIznos(int iznos) {
		this.iznos = iznos;
	}

	/*public Date getMesec() {
		return this.mesec;
	}

	public void setMesec(Date mesec) {
		this.mesec = mesec;
	}*/

	public Osoba getOsoba() {
		return this.osoba;
	}

	public void setOsoba(Osoba osoba) {
		this.osoba = osoba;
	}
	
	public Paket getPaket() {
		return this.paket;
	}

	public void setPaket(Paket tpaket) {
		this.paket = tpaket;
	}
	
}