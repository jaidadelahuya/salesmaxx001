package com.salesmaxx.persistence.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;
import com.salesmaxx.entities.EventTemplate;
import com.salesmaxx.entities.exception.RollbackFailureException;

public class EventTemplateController {

	private static DatastoreService ds = EMF.getDs();
	private static EntityManagerFactory emf = EMF.get();
	private Transaction txn = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(EventTemplate eventTemplate) throws RollbackFailureException,
			Exception {
		EntityManager em = null;
		try {
			txn = ds.beginTransaction();
			em = getEntityManager();
			em.persist(eventTemplate);
			txn.commit();
		} catch (Exception ex) {
			try {
				txn.rollback();
			} catch (Exception re) {
				throw new RollbackFailureException(
						"An error occurred attempting to roll back the transaction.",
						re);
			}

			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void edit(EventTemplate eventTemplate) throws RollbackFailureException,
			Exception {
		create(eventTemplate);
	}

	public void destroy(Key key) throws RollbackFailureException, Exception {
		EntityManager em = null;
		try {
			txn = ds.beginTransaction();
			em = getEntityManager();
			EventTemplate eventTemplate;
			eventTemplate = em.getReference(EventTemplate.class, key);
			em.remove(eventTemplate);
			txn.commit();
		} catch (Exception ex) {
			try {
				txn.rollback();
			} catch (Exception re) {
				throw new RollbackFailureException(
						"An error occurred attempting to roll back the transaction.",
						re);
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public EventTemplate findEventTemplate(Key key) {
		EntityManager em = getEntityManager();
		try {
			EventTemplate a = em.find(EventTemplate.class, key);
			return a;
		} finally {
			em.close();
		}
	}
	
	public List<EventTemplate> getAllEventTemplatesKeys() {
		Query q = new Query("EventTemplate");
		q.addSort("name");
		PreparedQuery pq = ds.prepare(q);
		
		List<EventTemplate> temps = new ArrayList<EventTemplate>();
		String name = null;
		String id = null;
		for(Entity result: pq.asIterable()) {
			name =(String) result.getProperty("name");
			id = result.getKey().getName();
			EventTemplate et = new EventTemplate(id);
			et.setName(name);
			temps.add(et);
			et = null;
		}
		return temps;
	}

}
