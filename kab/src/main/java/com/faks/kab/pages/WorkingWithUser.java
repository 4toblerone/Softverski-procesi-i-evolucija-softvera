package com.faks.kab.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Grid;
import org.apache.tapestry5.corelib.components.Select;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.SelectModelFactory;
import org.testng.remote.strprotocol.IMessage;

import com.faks.kab.anotations.RequiresLogIn;
import com.faks.kab.entities.Grad;
import com.faks.kab.entities.Osoba;
import com.faks.kab.entities.Paket;
import com.faks.kab.entities.Role;
import com.faks.kab.services.CrudDAO;
import com.faks.kab.util.QueryParamsMaker;

@RequiresLogIn
@Import(stylesheet = "context:css/tblPaketi/tblpaketi.css")
public class WorkingWithUser {

	@Inject
	private EntityManager em;

	@Inject
	private SelectModelFactory selectModelFactory;

	@Inject
	private CrudDAO crudDAO;

	@Component
	private Form addUser;

	@Component
	private Select select;
	
	@Component
	private Select selectpaket;

	//@Component
	//@Property
	//private Grid list;

	@Property
	@Persist
	private Paket paket;

	@Property
	private int pttGrada;
	
	@Property
	private String paketID;

	@Property
	private Map<Integer, String> ptt_nazivgrada;// = new HashMap<Integer,
												// String>();
	
	@Property
	private Map<String , String> id_naz_brz_cena;

	@Property
	@Persist
	private List<Paket> listaPaketa;

	@Property
	@Persist
	private String name;

	@Property
	@Persist
	private String surname;

	@Property
	@Persist
	private String streetName;

	@Property
	@Persist
	private String houseNumber;

	@Property
	@Persist
	private String JMBG;

	@Property
	
	private String phoneNumber;

	public void onActivate() {

		List<Grad> listaGradova = crudDAO.findAll(Grad.class);
		listaPaketa = crudDAO.findAll(Paket.class);
		// List<String> listaImena = new ArrayList<String>();
		// zbog ove ovde
		// inicijalizacije nije hteo da pozove onSuccess, zasto?!
		// Uvek se prvo poziva aktivacija, tj pasivacija->aktivacija->dogadjaj
		ptt_nazivgrada = new HashMap<Integer, String>();
		
		id_naz_brz_cena = new HashMap<String , String>();
		paketSelectorMaker();
		for (Grad grad : listaGradova) {

			ptt_nazivgrada.put(grad.getPtt(), grad.getNazivGrada());

		}
		
	}

	private void paketSelectorMaker(){
		
		for(Paket paket : listaPaketa){
			
			id_naz_brz_cena.put(paket.getPaketID(), paket.getNazivPaketa()+","+paket.getBrzina()+","+paket.getCenaPaketa());
		}
		
		
	}
	
	public void onSuccessFromAddUser() {

		Grad grad = (Grad) crudDAO.findUniqueWithNamedQuery(Grad.BY_PTT,
				QueryParamsMaker.with("ptt", pttGrada).parameters());
		// System.out.println(grad.getNazivGrada()); radi

		Paket paket = (Paket)crudDAO.findUniqueWithNamedQuery(Paket.BY_PAKET_ID,QueryParamsMaker.with("paketID", paketID).parameters() );
		
		Osoba osoba = new Osoba(JMBG, name, surname, "1121312", streetName, "dfasd", grad, Role.USER);
		osoba.setPaket(paket);
		
		 crudDAO.create(osoba);
		 
		 Osoba o = new Osoba("probaWorkingWU");
		 o.setUloga(Role.USER);
		 crudDAO.create(o);
		
		//Pamcenje objekta se ne izvrsava...
		
		
		// Osoba osoba = new Osoba(JMBG, name, surname, phoneNumber, streetName,
		// houseNumber, paket, grad);
		// proveri da li moze da upise tu osobu, tj da li vec postoji u bazi,
		// verovatno ce enitityManager to automatski da uradi

		// aktiviraj dogadjaj uz pomoc JavaScript-a , tako da se zatamni
		// pozadina i prikaze ekran sa podacima
		// gde radnik revidira unete podatke
		// crudDAO.create(osoba);

		
	}

	/*
	 * Naci nacina da selektovanje tabele bude input u formu da se ne aktivira
	 * dogadjaj i salje novi zahtev...
	 */
	public Object onSelected(String paket) {

		System.err.println(paket);

		return null;
	}

}
