package com.faks.kab.components;

import java.io.IOException;

import javax.management.RuntimeErrorException;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.ComponentEventCallback;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.PasswordField;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.corelib.internal.InternalMessages;
import org.apache.tapestry5.internal.services.ComponentResultProcessorWrapper;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.InjectService;
import org.apache.tapestry5.services.ComponentEventResultProcessor;
import org.apache.tapestry5.services.PageRenderLinkSource;

import com.faks.kab.entities.Osoba;
import com.faks.kab.pages.Index;
import com.faks.kab.services.Authenticator;

public class SignIn {

	@Inject
	private ComponentResources componentResources;

	@Inject
	private PageRenderLinkSource pageRenderLinkSource;

	@Inject
	private Authenticator authenticator;

	@InjectService("AjaxComponentEventResultProcessor")
	private ComponentEventResultProcessor<Block> componentEventResultProcessor;

	@Persist
	private String userName;

	private String password;

	@Component
	private Form signinform;

	@Parameter(defaultPrefix = BindingConstants.LITERAL)
	@Property
	@SuppressWarnings("unused")
	private String zone;

	@SessionState
	private Osoba osoba;

	boolean onSuccessFromSignInForm() throws Exception { 
		// call autheticator
															// which need to
		// call CrudDAO service and check if that user exists

		Object[] o = new Object[1];
		osoba = authenticator.login(getUserName(), getPassword());
		o[0] = osoba;

		ComponentResultProcessorWrapper callback = new ComponentResultProcessorWrapper(
				componentEventResultProcessor);

		componentResources.triggerEvent("proba", o, callback);

		return false;

	}

	public String getPassword() {

		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {

		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
