package com.faks.kab.services;

import java.util.List;
import java.util.Map;

import org.tynamo.jpa.annotations.CommitAfter;

import com.faks.kab.entities.DomainEntity;

public interface CrudDAO {

	//persists entity which DomainEntity and returns it
	// first check if object all ready exists in database ? 
	
	@CommitAfter
	<T> T create(T t);
	
	//finds entity which implements DomainEntity and returns it
	
	@CommitAfter
	<T> T find(T t);
	
	
	
	//deletes entity which implements DomainEntity 
	@CommitAfter
	<T> void delete(T t);
	
	@CommitAfter
	<T> T update(T t);
	
	
	@CommitAfter
	DomainEntity findUniqueWithNamedQuery(String queryName);
	
	@CommitAfter
	DomainEntity findUniqueWithNamedQuery(String queryName, Map<String, Object> params) ;
	
	
	//returns a whole "table" in form of a list
	@CommitAfter
	<T> List<T> findAll(Class<T> t);
	
}
