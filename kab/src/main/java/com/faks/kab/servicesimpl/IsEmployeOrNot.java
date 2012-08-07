package com.faks.kab.servicesimpl;

import java.io.IOException;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.ioc.Resource;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ComponentEventRequestParameters;
import org.apache.tapestry5.services.ComponentRequestFilter;
import org.apache.tapestry5.services.ComponentRequestHandler;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.apache.tapestry5.services.PageRenderRequestParameters;

import com.faks.kab.services.Authenticator;

public class IsEmployeOrNot implements ComponentRequestFilter {

	/*
	 * NOTE TO MYSELF: you should create some kind of role-based-annotation maker or use external libraries like
	 * 
	 * http://docs.codehaus.org/display/TYNAMO/tapestry-security+guide
	 * 
	 * */
	
	@Inject
	private ComponentResources componentResources;
	
	@Inject 
	private PageRenderLinkSource pageRenderLinkSource;
	
	@Inject
	private Resource resource;
	
	@Inject
	private Authenticator authenticator;
	
	public void handleComponentEvent(
			ComponentEventRequestParameters parameters,
			ComponentRequestHandler handler) throws IOException {
		// TODO Auto-generated method stub
		
	}

	public void handlePageRender(PageRenderRequestParameters parameters,
			ComponentRequestHandler handler) throws IOException {
		// TODO Auto-generated method stub
		
	}
	
	

	
	
}
