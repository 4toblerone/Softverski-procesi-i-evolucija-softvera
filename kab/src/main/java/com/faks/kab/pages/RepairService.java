package com.faks.kab.pages;

import java.util.ArrayList;
import java.util.List;



import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;

import com.faks.kab.anotations.RequiresLogIn;
import com.faks.kab.util.Malfunctions;

@RequiresLogIn
public class RepairService {

	@Inject
	private Request request;
	
	@Property
	@InjectComponent
	private Zone zone;
	
	@Property
	@Persist
	private List<Malfunctions> kvarovi;
	
	@Property
	private Malfunctions kvar;
	
	public void onActivate(){
		
		kvarovi = new ArrayList<Malfunctions>();
		for(Malfunctions malfunction : Malfunctions.values()){
			kvarovi.add(malfunction);
			
		}
	}
	
	public boolean onSuccess(){
		
	String [] c =request.getParameters("proba");
		for (String string : c) {
				
				
			
		}
		
		return true;
	}
	
}
