package com.faks.kab.encoders;

import javax.persistence.EntityManager;

import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ValueEncoderFactory;

import com.faks.kab.entities.DomainEntity;
import com.faks.kab.entities.Grad;
import com.faks.kab.services.CrudDAO;

public class DomainEntityEncoder implements ValueEncoder<Grad>, ValueEncoderFactory<Grad>{

	@Inject
	private CrudDAO crudDAO;
	
	@Inject
	private EntityManager em;
	
	public ValueEncoder<Grad> create(Class<Grad> type) {
		return this;
	}

	public String toClient(Grad value) {
		return String.valueOf(value.getPtt());
	}

	public Grad toValue(String clientValue) {
		return em.find(Grad.class, clientValue );
	}

	

	

}
