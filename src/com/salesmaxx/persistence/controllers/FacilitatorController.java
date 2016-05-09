package com.salesmaxx.persistence.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;
import com.salesmaxx.entities.Facilitator;
import com.salesmaxx.entities.exception.RollbackFailureException;
import com.salesmaxx.util.Util;

public class FacilitatorController {
	private static DatastoreService ds = EMF.getDs();
	private static EntityManagerFactory emf = EMF.get();
	private Transaction txn = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(Facilitator facilitator){
		Entity ent = Util.facilitatorToEntity(facilitator);
		txn = ds.beginTransaction();
		ds.put(ent);
		txn.commit();
	}

	public void edit(Facilitator facilitator) throws RollbackFailureException,
			Exception {
		create(facilitator);
	}

	public void destroy(Facilitator fac) throws RollbackFailureException, Exception {
		txn = ds.beginTransaction();
		ds.delete(fac.getId());
		txn.commit();
	}

	public Facilitator findFacilitator(Key k) {
		Entity e = null;
		try {
			e = ds.get(k);
			
			
		} catch (EntityNotFoundException enfe) {
			// TODO Auto-generated catch block
			enfe.printStackTrace();
			return null;
		}
		return Util.entityToFacilitator(e);
	}
	
	public List<Facilitator> findAllFacilitators() {
		Query q = new Query(Facilitator.class.getSimpleName());
		q.addSort("firstName");
		PreparedQuery pq = ds.prepare(q);
		List<Facilitator> facilitators = new ArrayList<>();
		for(Entity result: pq.asIterable()) {
			facilitators.add(Util.entityToFacilitator(result));
		}
		return facilitators;
	}
	
	public List<Key> getFacilitatorsKeys() {
		Query q = new Query(Facilitator.class.getSimpleName());
		q.setKeysOnly();
		PreparedQuery pq = ds.prepare(q);
		Iterator<Entity> e = pq.asIterator();
		List<Key> kys = new ArrayList<>();
		while (e.hasNext()) {
			kys.add(e.next().getKey());
		}
		return kys;
	}

}
