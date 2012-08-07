package com.faks.kab.servicesimpl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.apache.tapestry5.func.F;
import org.apache.tapestry5.func.Predicate;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.tynamo.jpa.annotations.CommitAfter;

import com.faks.kab.entities.DomainEntity;
import com.faks.kab.entities.Osoba;
import com.faks.kab.services.CrudDAO;

public class CrudDAOImpl implements CrudDAO {

	@Inject
	private EntityManager entityManager;

	@CommitAfter
	public <T> T create(T t) {
		
		entityManager.persist(t);
		
		return t;
		
	}

	
	public <T> T find(T t) {
		// TODO Auto-generated method stub
		return null;
	}

	@CommitAfter
	public <T> void delete(T t) {
		//entityManager.remove(t);
		entityManager.remove(entityManager.merge(t));
	}
	
	@CommitAfter
	public <T> T update(T t){
		entityManager.merge(t);
		return t;
	}

	public DomainEntity findUniqueWithNamedQuery(String queryName) {

		return (DomainEntity) entityManager.createNamedQuery(queryName)
				.getSingleResult();

	}

	/* @SuppressWarnings("unchecked")
    public <T> T findUniqueWithNamedQuery(String queryName, Map<String, Object> params)
    {
        Set<Entry<String, Object>> rawParameters = params.entrySet();
        Query query = session.getNamedQuery(queryName);

        for (Entry<String, Object> entry : rawParameters)
        {
            query.setParameter(entry.getKey(), entry.getValue());

        }
        return (T) query.uniqueResult();
    }*/
	@CommitAfter
	public DomainEntity findUniqueWithNamedQuery(String queryName,
			Map<String, Object> params) {

		Set<Entry<String, Object>> parameters = params.entrySet();
		Query query = null;
		try {

			query = entityManager.createNamedQuery(queryName);
		} catch (Exception e) {
		}
		for (Entry<String, Object> entry : parameters) {

			query.setParameter(entry.getKey(), entry.getValue());

		}

		if (query.getResultList().size() == 1)

			return (DomainEntity) query.getSingleResult();
		else
			return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> List<T> findAll(final Class<T> clazz) {

		String c = null;
		try {
			Field queryName = clazz.getField("ALL");
			c = (String) queryName.get(clazz);
		} catch (SecurityException e) {

			e.printStackTrace();
			System.err.println("Doslo je do greske u findAll");
		} catch (NoSuchFieldException e) {

			e.printStackTrace();
			System.err.println("Doslo je do greske u findAll");
		}

		catch (IllegalArgumentException e) {

			e.printStackTrace();
			System.err.println("Doslo je do greske u findAll");
		} catch (IllegalAccessException e) {

			e.printStackTrace();
			System.err.println("Doslo je do greske u findAll");
		}

		Query query = entityManager.createNamedQuery(c);

		List lista = query.getResultList();
		// use of F class,
		// get more time to study F class and use of it!!!
		//uopste ne udje u accept metodu
		//napraviti listu sa vise razlicitih objekata i testirati flow!!! 
		/*F.flow(lista).filter(new Predicate() {

			@Override
			public boolean accept(Object object) {
				
				
				System.err.println(object.getClass().getName());
				return (!object.getClass().isInstance(clazz)); 
				 
			}
		
		});*/

		return lista;
	}

}
