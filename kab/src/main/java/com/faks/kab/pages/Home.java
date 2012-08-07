package com.faks.kab.pages;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.faks.kab.entities.Osoba;
import com.faks.kab.services.Authenticator;

public class Home {

	@Property
	private Osoba osoba;

	@Inject
	private Authenticator authenticator;

	public void onActivate() {

		osoba = authenticator.getLogedUser();
	}

}
