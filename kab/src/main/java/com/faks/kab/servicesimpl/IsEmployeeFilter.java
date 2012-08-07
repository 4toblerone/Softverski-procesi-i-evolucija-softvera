package com.faks.kab.servicesimpl;

import java.io.IOException;

import org.apache.tapestry5.services.ComponentEventRequestParameters;
import org.apache.tapestry5.services.ComponentRequestFilter;
import org.apache.tapestry5.services.ComponentRequestHandler;
import org.apache.tapestry5.services.ComponentSource;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.apache.tapestry5.services.PageRenderRequestParameters;
import org.apache.tapestry5.services.Response;

import com.faks.kab.services.Authenticator;

public class IsEmployeeFilter implements ComponentRequestFilter {

	private final PageRenderLinkSource renderLinkSource;

	private final ComponentSource componentSource;

	private final Response response;

	private final Authenticator authenticatorImpl;

	public IsEmployeeFilter(PageRenderLinkSource renderLinkSource,
			ComponentSource componentSource, Response response,
			Authenticator authenticatorImpl) {
		this.renderLinkSource= renderLinkSource;
		this.componentSource= componentSource;
		this.response= response;
		this.authenticatorImpl = authenticatorImpl;
		
	}

	public void handleComponentEvent(
			ComponentEventRequestParameters parameters,
			ComponentRequestHandler handler) throws IOException {
		// TODO Auto-generated method stub

	}

	public void handlePageRender(PageRenderRequestParameters parameters,
			ComponentRequestHandler handler) throws IOException {
		// TODO Auto-generated method stub

	}
	
	/*private boolean dispatchedToLoginPage(String pagename,) {
		if(authenticatorImpl.isLogedIn() && authenticatorImpl.)
		{
			return false;
		}
		
		
	}
	*/
	
	
	
	
	
	
	
	
	
	
	
	

}
