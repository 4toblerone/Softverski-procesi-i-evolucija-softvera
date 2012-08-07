package com.faks.kab.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class DugovanjePK implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private String korisnikID;

	@Temporal(TemporalType.DATE)
	private Date mesec;

	public DugovanjePK() {

	}

	public String getKorisnikID() {
		return korisnikID;
	}

	public void setKorisnikID(String korisnikID) {
		this.korisnikID = korisnikID;

	}

	public Date getMesec() {

		return mesec;
	}

	public void setMesec(Date mesec) {

		this.mesec = mesec;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DugovanjePK)) {
			return false;
		}

		DugovanjePK castOther = (DugovanjePK) other;
		return this.mesec.equals(castOther.mesec)
				&& this.korisnikID.equals(castOther.korisnikID);

	}
	
	@Override
	public int hashCode() {
		final int prime = 33;
		int hash = 19;
		hash = hash * prime + this.mesec.hashCode();
		hash = hash * prime + this.korisnikID.hashCode();
		
		return hash;
	}
	

}
