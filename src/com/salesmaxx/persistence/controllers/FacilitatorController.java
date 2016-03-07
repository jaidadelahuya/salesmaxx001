package com.salesmaxx.persistence.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
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
		ds.delete(KeyFactory.createKey(Facilitator.class.getSimpleName(), fac.getId()));
		txn.commit();
	}

	public Facilitator findFacilitator(String id) {
		EntityManager em = getEntityManager();
		try {
			Facilitator a = em.find(Facilitator.class, id);
			return a;
		} finally {
			em.close();
		}
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

}
