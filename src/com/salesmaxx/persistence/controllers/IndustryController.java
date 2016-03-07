package com.salesmaxx.persistence.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;
import com.salesmaxx.entities.Industry;
import com.salesmaxx.entities.exception.RollbackFailureException;

public class IndustryController {
	private static DatastoreService ds = EMF.getDs();
	private Transaction txn = null;

	public void create(Industry industry) {
		Entity ent = new Entity("Industry", industry.getName().getName());
		txn = ds.beginTransaction();
		ds.put(ent);
		txn.commit();
	}

	public void edit(Industry industry)
			throws RollbackFailureException, Exception {
		create(industry);
	}

	public void destroy(Industry industry)
			throws RollbackFailureException, Exception {
		txn = ds.beginTransaction();
		ds.delete(industry.getName());
		txn.commit();
	}

	public List<Industry> getAllIndustries() {
		Query q = new Query("Industry");
		PreparedQuery pq = ds.prepare(q);

		List<Industry> temps = new ArrayList<Industry>();
		for (Entity result : pq.asIterable()) {
			Industry wt = new Industry((String) result.getKey().getName());
			temps.add(wt);
		}
		return temps;
	}
}
