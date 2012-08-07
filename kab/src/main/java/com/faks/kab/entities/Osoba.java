/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faks.kab.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author sale
 */
@Entity
@Table(name = "TOsoba")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = Osoba.ALL, query = "SELECT t FROM Osoba t"),
		@NamedQuery(name = Osoba.BY_JMBG, query = "SELECT t FROM Osoba t WHERE t.jmbg = :jmbg"),
		@NamedQuery(name = Osoba.BY_IME, query = "SELECT t FROM Osoba t WHERE t.ime = :ime"),
		@NamedQuery(name = "Osoba.findByPrezime", query = "SELECT t FROM Osoba t WHERE t.prezime = :prezime"),
		@NamedQuery(name = "Osoba.findByTelefon", query = "SELECT t FROM Osoba t WHERE t.telefon = :telefon"),
		@NamedQuery(name = "Osoba.findByUlica", query = "SELECT t FROM Osoba t WHERE t.ulica = :ulica"),
		@NamedQuery(name = "Osoba.findByBroj", query = "SELECT t FROM Osoba t WHERE t.broj = :broj"),
		//@NamedQuery(name = "Osoba.findByPtt", query = "SELECT t FROM Osoba t WHERE t.ptt = :ptt"),
		@NamedQuery(name = Osoba.BY_JMBG_I_IME, query ="SELECT t FROM Osoba t WHERE t.jmbg = :jmbg and t.ime = :ime"),
		
		//  @NamedQuery(name = User.BY_CREDENTIALS, query = "Select u from User u where u.username = :username and u.password = :password")
// @NamedQuery(name = "Osoba.findByPaketID", query =
// "SELECT t FROM Osoba t WHERE t.paketID = :paketID")
})
public class Osoba implements Serializable, DomainEntity {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "JMBG")
	private String jmbg;
	@Basic(optional = false)
	@Column(name = "ime")
	private String ime;
	@Basic(optional = false)
	@Column(name = "prezime")
	private String prezime;
	@Basic(optional = false)
	@Column(name = "telefon")
	private String telefon;
	@Basic(optional = false)
	@Column(name = "ulica")
	private String ulica;
	@Basic(optional = false)
	@Column(name = "broj")
	private String broj;
	/*@Basic(optional = false)
	@Column(name = "ptt")
	private int ptt;*/
	@Basic(optional = false)
	@Column(name = "uloga")
	private String uloga;

	@Transient
	private Role tipUloge;

	public Role getTipUloge() {

		return Role.valueOf(uloga);
	}

	public void setUloga(Role tipUloge) {

		this.uloga = tipUloge.name();
	}

	@OneToMany(mappedBy = "osoba", cascade = { CascadeType.ALL })
	private List<Dugovanje> dugovanja;

	@ManyToOne
	@JoinColumn(name="ptt")
	private Grad grad;
	
	@ManyToOne
	@JoinColumn(name = "paketID")
	private Paket paket;

	@OneToMany(mappedBy = "tosoba", cascade = { CascadeType.ALL })
	private List<Kvar> tkvars;

	/**/

	public List<Kvar> getTkvars() {
		return this.tkvars;
	}

	public void setTkvars(List<Kvar> tkvars) {
		this.tkvars = tkvars;
	}

	public Osoba() {
	}

	public Osoba(String jmbg) {
		this.jmbg = jmbg;
	}

	public static final String ALL = "Osoba.findAll";

	public static final String BY_JMBG = "Osoba.findByJmbg";

	public static final String BY_IME = "Osoba.findByIme";
	
	public static final String BY_JMBG_I_IME = "Osoba.findByJmbgAndIme";

	public Osoba(String jmbg, String ime, String prezime, String telefon,
			String ulica, String broj,Grad grad, Role tipUloge) {
		this.jmbg = jmbg;
		this.ime = ime;
		this.prezime = prezime;
		this.telefon = telefon;
		this.ulica = ulica;
		this.broj = broj;
		this.grad = grad;
		this.tipUloge = tipUloge;

	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getUlica() {
		return ulica;
	}

	public void setUlica(String ulica) {
		this.ulica = ulica;
	}

	public String getBroj() {
		return broj;
	}

	public void setBroj(String broj) {
		this.broj = broj;
	}

	/*public int getPtt() {
		return ptt;
	}

	public void setPtt(int ptt) {
		this.ptt = ptt;
	}*/

	public Paket getPaket() {

		return paket;
	}

	public void setPaket(Paket paket) {

		this.paket = paket;
	}

	public List<Dugovanje> getDugovanja() {
		return this.dugovanja;
	}

	public void setDugovanja(List<Dugovanje> dugovanja) {
		this.dugovanja = dugovanja;
	}
	
	public void setGrad(Grad grad){
		this.grad = grad;
	}
	
	public Grad getGrad(){
		return this.grad;
	}
	
	

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (jmbg != null ? jmbg.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Osoba)) {
			return false;
		}
		Osoba other = (Osoba) object;
		if ((this.jmbg == null && other.jmbg != null)
				|| (this.jmbg != null && !this.jmbg.equals(other.jmbg))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.faks.com.faks.kab.components.Osoba[ jmbg=" + jmbg + " ]";
	}

}
