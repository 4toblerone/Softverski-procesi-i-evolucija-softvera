package com.faks.kab.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

/**
 * The primary key class for the TKvar database table.
 * 
 */
@Embeddable
public class KvarPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String korisnikID;

    //@Temporal( TemporalType.TIMESTAMP)
	private Timestamp vremePocetkaKvara;

    public KvarPK() {
    }
    
    public KvarPK(String korisnikID, Timestamp vremePocetkaKvara){
    	this.korisnikID = korisnikID;
    	this.vremePocetkaKvara = vremePocetkaKvara;
    	
    	
    }
	public String getKorisnikID() {
		return this.korisnikID;
	}
	public void setKorisnikID(String korisnikID) {
		this.korisnikID = korisnikID;
	}
	public java.util.Date getVremePocetkaKvara() {
		return this.vremePocetkaKvara;
	}
	public void setVremePocetkaKvara(Timestamp vremePocetkaKvara) {
		this.vremePocetkaKvara = vremePocetkaKvara;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof KvarPK)) {
			return false;
		}
		KvarPK castOther = (KvarPK)other;
		return 
			this.korisnikID.equals(castOther.korisnikID)
			&& this.vremePocetkaKvara.equals(castOther.vremePocetkaKvara);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.korisnikID.hashCode();
		hash = hash * prime + this.vremePocetkaKvara.hashCode();
		
		return hash;
    }
}