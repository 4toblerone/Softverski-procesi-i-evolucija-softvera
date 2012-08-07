package com.faks.kab.servicesimpl;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.Session;

import com.faks.kab.entities.Osoba;
import com.faks.kab.services.Authenticator;
import com.faks.kab.services.CrudDAO;
import com.faks.kab.util.AppConstants;
import com.faks.kab.util.QueryParamsMaker;

public class AutheticatorImpl implements Authenticator {

	@Inject
	private CrudDAO crudDAO;

	@Inject
	private Request request;

	//public static final String LOGED_USER = "logedUser";

	public Osoba login(String username, String password) {

		// napravi QueryParametar maker...
		Osoba osoba;
		
		/*
		 *  User user = crudService.findUniqueWithNamedQuery(User.BY_CREDENTIALS, QueryParameters.with(
                "username",
                username).and("password", password).parameters());
		 * */

		osoba = (Osoba) crudDAO.findUniqueWithNamedQuery(Osoba.BY_JMBG_I_IME, QueryParamsMaker.with("ime", username).and("jmbg", password).parameters());
		//osoba = (Osoba) crudDAO.findUniqueWithNamedQuery(Osoba.BY_JMBG,
			//	QueryParamsMaker.with("jmbg", password).parameters());

		// ukoliko ne postoji takav korisnik odvesti korisnika na signUp stranu
		request.getSession(true).setAttribute(AppConstants.LOGED_USER, osoba);

		if (osoba != null)
			return osoba;
		else
			return null;
	}

	public boolean isLogedIn() {
		Session session = request.getSession(false);
		if (session != null) {

			return session.getAttribute(AppConstants.LOGED_USER) != null;
		}
		return false;
	}

	public Osoba getLogedUser() {
		Session session = request.getSession(false);
		Osoba osoba = (Osoba) session.getAttribute(AppConstants.LOGED_USER);
		return osoba;
	}

	public void logOut() {
		
		//izbrisi iz sesije...
		// TODO Auto-generated method stub

	}

}
