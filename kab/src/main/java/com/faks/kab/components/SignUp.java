package com.faks.kab.components;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;

import com.faks.kab.pages.Index;

public class SignUp {
	

	@Property
	private String firstname;
	
	@Property 
	private String lastname;

	@Component(id="signupform")
	private Form form;
	
	@Component(id="firstname")
	private TextField textFieldName;
	
	@Component(id="lastname")
	private TextField textFieldLasName;
	

    @Inject
    private PageRenderLinkSource pageRenderLinkSource;
	
	
	public Object onSuccess(){
		
		
		return pageRenderLinkSource.createPageRenderLink(Index.class);
	}
}
