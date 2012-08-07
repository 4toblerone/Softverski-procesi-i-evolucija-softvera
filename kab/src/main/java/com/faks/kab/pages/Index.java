package com.faks.kab.pages;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.EntityManager;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;

import com.faks.kab.components.SignIn;
import com.faks.kab.components.SignUp;
import com.faks.kab.entities.Dugovanje;
import com.faks.kab.entities.Grad;
import com.faks.kab.entities.Kvar;
import com.faks.kab.entities.Osoba;
import com.faks.kab.entities.Paket;
import com.faks.kab.entities.Role;
import com.faks.kab.services.Authenticator;
import com.faks.kab.services.CrudDAO;
import com.faks.kab.util.QueryParamsMaker;

/**
 * Start page of application com.faks.kab.
 */
public class Index {

	@Inject
	private PageRenderLinkSource pageRenderLinkSource;

	@Property
	@InjectComponent
	private Zone loginzone;

	@Component
	private SignUp signUp;

	@Component
	private SignIn singin;

	@Inject
	private Block loginblock;

	@Inject
	private Authenticator authenticator;

	@Inject
	private CrudDAO crudDAO;

	@Inject
	private EntityManager em;

	@Property
	private int numbOfLogInAttempts; // zasto kad inicijalizuje u onActivate
										// prilikom svakog zahteva postavi polje
										// opet na 0?

	@Persist
	private Paket paket;

	public void setPaket(Paket paket) {

		this.paket = paket;
	}

	public Paket getPaket() {

		return paket;
	}

	public boolean onActivate() {
/*
		 * crudDAO.findUniqueWithNamedQuery
		 * (Osoba.BY_JMBG,QueryParamsMaker.with("jmbg",
		 * "1202988101501").parameters());
		 * 
		 * @SuppressWarnings("unchecked") Osoba o = (Osoba)
		 * crudDAO.findUniqueWithNamedQuery(Osoba.ALL); //Osoba o =
		 * lista.get(0); Kvar kvar = new Kvar("Fda", o, sqlDate);
		 * 
		 * crudDAO.create(kvar);
		 */
		/*
		  Paket bwo = (Paket)
		  crudDAO.findUniqueWithNamedQuery(Paket.BY_PAKET_ID,
		  QueryParamsMaker.with("paketID", "proba paket").parameters()); Osoba
		  o = (Osoba) crudDAO.findUniqueWithNamedQuery(Osoba.ALL);
		 * 
		 * 
		*/
		/*
		Grad grad = (Grad)
		  crudDAO.findUniqueWithNamedQuery(Grad.BY_PTT,
		  QueryParamsMaker.with("ptt", 11000).parameters());
		
		
		Osoba osoba = new Osoba("salecar");
		osoba.setGrad(grad);
		crudDAO.create(osoba);
		*/
		/*Osoba a  = new Osoba("aaafdsaf");
		a.setUloga(Role.USER);
		crudDAO.create(a);*/ 
		//pamcenje radi!!!
		/*ArrayList<String> bla = new ArrayList<String>();
		bla.add(0, "prvi");
		bla.add(1, "drugi");
		
		for(int k = 0 ; k<bla.size(); k++){
			
			System.err.println(bla.get(k));
		}*/
		return true;
	}

	public Date getCurrentTime() {

		/*
		 * Adresa a = new Adresa(19, "fdsaf", 1, 100); entityManager.persist(a);
		 */
		for (Osoba osoba : crudDAO.findAll(Osoba.class)) {
			System.err.println(osoba.getIme());
		}
		return new Date();
	}

	public Object onProba(Object[] o) {

		// ako tri puta promasi pass , vrati mu sign up block

		Osoba osoba = (Osoba) o[0];

		if (osoba == null) {
			if (numbOfLogInAttempts <= 2) {
				numbOfLogInAttempts++;
				// izrenderuj opet SignIn
				return loginzone.getBody();
			} else {
				// izrenderuj SignUp
				return loginblock;

			}

		} else {

			// perzistuj osobu...
			return pageRenderLinkSource.createPageRenderLink(BandwidthOffer.class);
		}

	}

}
