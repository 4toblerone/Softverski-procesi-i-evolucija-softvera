package com.faks.kab.servicesimpl;

import java.io.IOException;

import org.apache.tapestry5.Link;
import org.apache.tapestry5.runtime.Component;
import org.apache.tapestry5.services.ComponentEventRequestParameters;
import org.apache.tapestry5.services.ComponentRequestFilter;
import org.apache.tapestry5.services.ComponentRequestHandler;
import org.apache.tapestry5.services.ComponentSource;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.apache.tapestry5.services.PageRenderRequestParameters;
import org.apache.tapestry5.services.Response;

import com.faks.kab.anotations.RequiresLogIn;
import com.faks.kab.services.Authenticator;

public class IsUserLogedInFilter implements ComponentRequestFilter {

	/*
	 * we use this filter to check if user is log in or not so we could redirect
	 * him (or not) to SignIn page
	 * 
	 * detailed explanation of the code below can be found on
	 * http://tapestryjava
	 * .blogspot.com/2009/12/securing-tapestry-pages-with.html
	 */

	private final PageRenderLinkSource renderLinkSource;

	private final ComponentSource componentSource;

	private final Response response;

	private final Authenticator authenticatorImpl;

	
	
	public IsUserLogedInFilter(PageRenderLinkSource renderLinkSource,
			ComponentSource componentSource, Response response,
			Authenticator autheticatorImpl) {
		this.renderLinkSource = renderLinkSource;
		this.componentSource = componentSource;
		this.response = response;
		this.authenticatorImpl = autheticatorImpl;
	}

	public void handleComponentEvent(
			ComponentEventRequestParameters parameters,
			ComponentRequestHandler handler) throws IOException {

		if (dispatchedToLoginPage(parameters.getActivePageName())) {
			return;
		}

		handler.handleComponentEvent(parameters);

	}

	public void handlePageRender(PageRenderRequestParameters parameters,
			ComponentRequestHandler handler) throws IOException {

		if (dispatchedToLoginPage(parameters.getLogicalPageName())) {
			return;
		}

		handler.handlePageRender(parameters);
	}

	private boolean dispatchedToLoginPage(String pagename) throws IOException {
		if (authenticatorImpl.isLogedIn()) {
			return false;
		}

		Component page = componentSource.getPage(pagename);

		if (!page.getClass().isAnnotationPresent(RequiresLogIn.class)) {

			return false;
		}

		Link link = renderLinkSource.createPageRenderLink("Index");
		response.sendRedirect(link);
		return true;
	}

}
