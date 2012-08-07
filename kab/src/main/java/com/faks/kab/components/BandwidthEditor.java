package com.faks.kab.components;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.ComponentEventCallback;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.internal.services.ComponentResultProcessorWrapper;
import org.apache.tapestry5.internal.structure.Page;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.InjectService;
import org.apache.tapestry5.services.ComponentEventResultProcessor;
import org.apache.tapestry5.services.PageRenderLinkSource;

import com.faks.kab.entities.Paket;
import com.faks.kab.pages.Index;
import com.faks.kab.services.CrudDAO;
import com.faks.kab.util.QueryParamsMaker;

public class BandwidthEditor {

	@Inject
	private CrudDAO crudDAO;

	@Inject
	private ComponentResources componentResources;

	@Component
	private Form reviewForm;

	@Property
	private String nazivPaketa;

	@Property
	private String brzina;

	@Property
	private String opisPaketa;

	@Property
	private String cenaPaketa;

	@Component(id = "nazivPaketa")
	private TextField nazivPaketaTxtF;

	@Component(id = "brzina")
	private TextField brzinaTxtF;

	@Component(id = "opisPaketa")
	private TextField opisPaketaTxtF;

	@Component(id = "cenaPaketa")
	private TextField cenaPaketaTxtF;

	@InjectService("AjaxComponentEventResultProcessor")
	private ComponentEventResultProcessor<Object> componentEventResultProcessor;

	@Parameter
	@Property
	private String paketID;

	@Parameter(defaultPrefix = BindingConstants.LITERAL)
	@Property
	@SuppressWarnings("unused")
	private String zone;

	@Property
	@Persist
	private Paket bwo;

	@Property
	private boolean isBandWidthOfferNull;

	@InjectPage
	private Index indeks;

	// private ComponentResultProcessorWrapper callback;

	public void setupRender() {

		if (paketID == null)
			isBandWidthOfferNull = true;

		else
			bwo = (Paket) crudDAO.findUniqueWithNamedQuery(Paket.BY_PAKET_ID,
					QueryParamsMaker.with("paketID", paketID).parameters());

	}

	public boolean onSuccess() {

		crudDAO.update(bwo);

		// radi s tim da mora da se bubble upuje i vrati zone opet...
		ComponentResultProcessorWrapper callback = new ComponentResultProcessorWrapper(
				componentEventResultProcessor);
		componentResources.triggerEvent("BandWidthOfferUpdate", null, callback);

		return false;

	}

	public boolean onDelete() {

		crudDAO.delete(bwo);

		ComponentResultProcessorWrapper callback = new ComponentResultProcessorWrapper(
				componentEventResultProcessor);
		componentResources.triggerEvent("BandWidthOfferDelete", null, callback);
		return false;

	}

}
