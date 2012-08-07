package com.faks.kab.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.ajax.MultiZoneUpdate;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Submit;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.got5.tapestry5.jquery.EffectsConstants;
import org.got5.tapestry5.jquery.mixins.Autocomplete;
import org.got5.tapestry5.jquery.services.EffectsParam;

import com.faks.kab.anotations.RequiresLogIn;
import com.faks.kab.entities.Dugovanje;
import com.faks.kab.entities.Osoba;
import com.faks.kab.services.CrudDAO;
import com.faks.kab.util.QueryParamsMaker;

@RequiresLogIn
public class Payment {

	// imace autocomplete mixin za trazenje osobe
	// po nalazanjeu pojavice lista sa svim dugovanjima
	// gde ce radnik moci da cekira dugovanja koja trebaju da budu izbrisana

	// imace samo listu sa prikazom svojih dugovanja po mesecima

	// sa zone komponentom napraviti tabelu u kojoj ce biti ispisana sva
	// dugovanja koja ce moci da se cekiraju i obrisu
	// tj. zavedu kao placena...

	// JPA je dobro implementiran!

	@Inject
	private Request request;

	@Inject
	private CrudDAO crudDAO;

	@InjectComponent
	private Zone tblZone2;

	@InjectComponent
	private Form pretragaForm;

	@InjectComponent
	private Zone tblZone;

	@Property
	@Persist
	private String name;

	@Property
	private List<String> list;

	@Property
	private List<Dugovanje> dugovanja;

	@Property
	@Persist
	private Dugovanje dugovanje;

	@Property
	@Persist
	private Osoba osoba;

	@Property
	private List<Dugovanje> dugavanjaZaBrisanje;

	public void onActivate() {

		// vrati sva imena i prezimena korisnika

		list = new ArrayList<String>();

		List<Osoba> listaOsoba = crudDAO.findAll(Osoba.class);

		for (Osoba osoba : listaOsoba) {

			list.add(osoba.getIme() + " " + osoba.getPrezime() + " "
					+ osoba.getUlica() + " " + osoba.getJmbg());

		}

	}

	@OnEvent(value = "provideCompletions")
	public List<String> autoComplete(String start) {

		// s tim sto treba da prikaze ime, prezime!!!

		List<String> sugestions = new ArrayList<String>();
		if (start != null) {

			for (String value : list) {
				if (value.toLowerCase().startsWith(start.toLowerCase()))

					sugestions.add(value);
			}

		}

		return sugestions;
	}

	public Object onSuccessFromPretragaForm() {

		String delimiter = "[ ]+";
		String[] reci = name.split(delimiter);
		String jmbg = reci[3];
		osoba = (Osoba) crudDAO.findUniqueWithNamedQuery(Osoba.BY_JMBG,
				QueryParamsMaker.with("jmbg", jmbg).parameters());
		dugovanja = osoba.getDugovanja();
		return getListAndEditorZones();
	}

	private MultiZoneUpdate getListAndEditorZones() {
		return new MultiZoneUpdate("tblZone", tblZone.getBody()).add(
				"tblZone2", tblZone2.getBody());

	}

	public Object onSuccessFromTabelaForma() {
		// izbrisi dugovanja
		for (Dugovanje d : dugavanjaZaBrisanje) {

			crudDAO.delete(d);
			dugovanja.remove(d);
		}

		return getListAndEditorZones();
	}

	public boolean isObrisi() {

		return false;
	}

	public void setObrisi(boolean obrisi) {

		if (obrisi) {
			dugavanjaZaBrisanje.add(dugovanje);

		}
	}

}
