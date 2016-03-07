package com.salesmaxx.persistence.controllers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.salesmaxx.entities.Address;
import com.salesmaxx.entities.Event;
import com.salesmaxx.entities.exception.RollbackFailureException;
import com.salesmaxx.util.Util;

public class EventController {

	private static DatastoreService ds = EMF.getDs();
	private static EntityManagerFactory emf = EMF.get();
	private Transaction txn = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(Event event, Address address) {
		Entity ent = Util.eventToEntity(event);
		Entity ent1 = Util.addressToEntity(address, ent.getKey());
		txn = ds.beginTransaction();
		ds.put(ent);
		ds.put(ent1);
		txn.commit();
	}

	public void edit(Event event) throws RollbackFailureException, Exception {
		Entity ent = Util.eventToEntity(event);
		txn = ds.beginTransaction();
		ds.put(ent);
		txn.commit();
	}

	public void destroy(Key key) throws RollbackFailureException, Exception {

		txn = ds.beginTransaction();
		ds.delete(key);
		txn.commit();
	}

	public Event findEvent(Key key) {
		EntityManager em = getEntityManager();
		try {
			Event a = em.find(Event.class, key);
			a.getSpeakers();
			return a;
		} finally {
			em.close();
		}
	}

}
