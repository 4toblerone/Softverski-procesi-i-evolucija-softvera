package com.faks.kab.components;

import java.util.List;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Grid;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.faks.kab.entities.Paket;
import com.faks.kab.services.CrudDAO;

public class BandwidthList {

	
	@Inject
	private CrudDAO crudDAO;
	
	@Inject
	private ComponentResources componentResources;
	
	@Property
	@Persist
	private List<Paket> bwolist;
	
	@Property
	@Parameter(defaultPrefix = BindingConstants.LITERAL)
	@SuppressWarnings("unused")
	private String zone;
	
	@Property
	private Paket bwo;
	
	@Component
	@Property
	private Grid list;
	
	public void setupRender(){
		
		bwolist = crudDAO.findAll(Paket.class);
		
	}
	
	public boolean onSelected(String paketID ){
		
		return false;
	}
	
}
