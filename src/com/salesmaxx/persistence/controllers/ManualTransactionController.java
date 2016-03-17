package com.salesmaxx.persistence.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Transaction;
import com.salesmaxx.entities.ManualTransaction;
import com.salesmaxx.util.Util;

public class ManualTransactionController {

	private static DatastoreService ds = EMF.getDs();
	private Transaction txn = null;
	
	public void create (ManualTransaction mt) {
		Entity e = Util.ManualTransactionToEntity(mt);
		txn = ds.beginTransaction();
		ds.put(e);
		txn.commit();
		
	}

	public ManualTransaction findByTxnRef(String txnRef) {
		Query q = new Query(ManualTransaction.class.getSimpleName());
		Filter filter =
				  new FilterPredicate("txnRef",
				                      FilterOperator.EQUAL,
				                      txnRef);
		q.setFilter(filter);
		PreparedQuery pq = ds.prepare(q);
		Entity e = pq.asSingleEntity();
		
		return Util.entityToManualTransaction(e);
	}

	public List<ManualTransaction> find(List<Key> mtKeys) {
		Map<Key, Entity> ents = ds.get(mtKeys);
		List<ManualTransaction> l = new ArrayList<>();
		for(Key k : ents.keySet()) {
			Entity e = ents.get(k);
			ManualTransaction mt = Util.entityToManualTransaction(e);
			l.add(mt);
		}
		return l;
	}
}
