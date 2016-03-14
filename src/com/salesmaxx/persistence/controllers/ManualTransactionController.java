package com.salesmaxx.persistence.controllers;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
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
}
