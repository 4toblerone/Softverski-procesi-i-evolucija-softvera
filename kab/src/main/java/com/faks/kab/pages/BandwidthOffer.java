package com.faks.kab.pages;


import org.apache.tapestry5.Block;
import org.apache.tapestry5.ajax.MultiZoneUpdate;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.Session;

import com.faks.kab.anotations.RequiresEmployeCredentials;
import com.faks.kab.anotations.RequiresLogIn;
import com.faks.kab.components.BandwidthList;
import com.faks.kab.entities.Osoba;
import com.faks.kab.entities.Paket;
import com.faks.kab.services.Authenticator;
import com.faks.kab.services.CrudDAO;
import com.faks.kab.util.AppConstants;

//@RequiresLogIn
//@RequiresEmployeCredentials
public class BandwidthOffer {

	@Inject
	private CrudDAO crudDAO;

	@Inject
	private Request request;

	@Inject
	private Block block;

	@Inject
	private Authenticator authenticator;

	@InjectComponent
	private Zone editorZone;

	@InjectComponent
	private Zone listZone;

	// @Component
	// private BandwidthList list;

	@Component
	private BeanEditForm bwobeanform;

	@Persist
	@Property
	private String paketID;

	@Property
	private Paket bwopackage;

	public void onActivate() {

		if (authenticator.isLogedIn()) {

			System.err.println("Ulogovan je");
			Session session = request.getSession(false);
			Osoba osoba = (Osoba) session.getAttribute(AppConstants.LOGED_USER);
			// Osoba osoba =(Osoba)
			// request.getAttribute(AppConstants.LOGED_USER);
			System.out.println(osoba.getTipUloge().name());
		}

		else
			System.err.println("Nije ulogovan");
	}

	public Object onBandWidthOfferUpdate() {

		return getListAndEditorZones();
	}

	public Object onBandWidthOfferDelete() {

		if (request.isXHR()) {

			System.err.println("Jeste");
		} else
			System.err.println("Nije");
		return request.isXHR() ? getListAndEditorZones() : null;
	}

	public Object onSelectedFromList(String paketID) {

		this.paketID = paketID;
		return request.isXHR() ? getListAndEditorZones() : null;

	}

	public Object onCreate() {

		return block;
	}

	public Object onSuccessFromBwobeanform() {

		System.err.println("fdasfafas");
		System.err.println(bwopackage.getNazivPaketa());
		crudDAO.create(bwopackage);
		bwopackage = new Paket();
		return getListAndEditorZones();
	}

	private MultiZoneUpdate getListAndEditorZones() {
		return new MultiZoneUpdate("listZone", listZone.getBody()).add(
				"editorZone", editorZone.getBody());

	}

}
