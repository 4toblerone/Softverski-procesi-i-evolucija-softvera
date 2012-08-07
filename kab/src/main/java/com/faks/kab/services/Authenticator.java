package com.faks.kab.services;

import com.faks.kab.entities.Osoba;

public interface Authenticator {

	
	Osoba login(String username, String password)  ;
	
	
	boolean isLogedIn();
	
	
	Osoba getLogedUser();
	
	
	void logOut();
	
}
