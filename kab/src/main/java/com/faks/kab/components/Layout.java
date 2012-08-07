package com.faks.kab.components;

import org.apache.tapestry5.*;

import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.*;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.Session;
import org.apache.tapestry5.BindingConstants;
import org.got5.tapestry5.jquery.components.Superfish;

import com.faks.kab.entities.Osoba;
import com.faks.kab.entities.Role;
import com.faks.kab.util.AppConstants;

/**
 * Layout component for pages of application com.faks.kab.
 */
@IncludeStylesheet("context:layout/layout.css")
public class Layout {
	/** The page title, for the <title> element and the <h1>element. */

	@Inject
	private ComponentResources resources;

	@Inject
	private Block employeeBlock, userBlock;

	@Inject
	private Request request;

	@Component
	private Superfish supfish;

	@Property
	@Parameter(required = true, defaultPrefix = BindingConstants.LITERAL)
	private String title;

	@Property
	private String pageName;

	// @Property
	// private boolean IsHideMenu;

	@Property
	@Parameter(defaultPrefix = BindingConstants.LITERAL)
	private String sidebarTitle;

	@Property
	@Parameter(defaultPrefix = BindingConstants.LITERAL)
	private Block sidebar;

	private Session session;

	public String getClassForPageName() {
		return resources.getPageName().equalsIgnoreCase(pageName) ? "current_page_item"
				: null;

	}

	public String[] getPageNames() {
		return new String[] { "Index", "About", "Contact" };
	}

	public boolean getIsHideMenu() {

		return AppConstants.PAGES_WITHOUT_MENU
				.contains(resources.getPageName());

	}

	public Object getUserTypeMenuBlock() {

		session = request.getSession(false);
		Osoba osoba = (Osoba) session.getAttribute(AppConstants.LOGED_USER);
		
		if (osoba.getTipUloge().equals(Role.EMPLOYEE)) {

			return employeeBlock;
		} else {

			return userBlock;
		}
	}

}
