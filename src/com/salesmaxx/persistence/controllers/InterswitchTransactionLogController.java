package com.salesmaxx.persistence.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyRange;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.datastore.TransactionOptions;
import com.salesmaxx.entities.InterswitchTransactionLog;
import com.salesmaxx.entities.ProductPaidFor;
import com.salesmaxx.entities.exception.RollbackFailureException;
import com.salesmaxx.util.Util;

public class InterswitchTransactionLogController {

	private static DatastoreService ds = EMF.getDs();
	private Transaction txn = null;

	public void create(InterswitchTransactionLog interswitchTransactionLog,
			List<ProductPaidFor> pdts) {
		Set<Key> pdtKeys = interswitchTransactionLog.getProductsPaidFor();
		List<Entity> ents = new ArrayList<>();
		if(pdts != null) {
			for (ProductPaidFor pdt : pdts) {
				KeyRange range = ds.allocateIds(
						ProductPaidFor.class.getSimpleName(), pdts.size());
				Iterator<Key> it = range.iterator();
				while (it.hasNext()) {
					Entity e = Util.productPaidForToEntity(pdt, it.next());
					ents.add(e);
					pdtKeys.add(e.getKey());
				}

			}
		}
		interswitchTransactionLog.setProductsPaidFor(pdtKeys);
		Entity e1 = Util
				.interswitchTransactionLogToEntity(interswitchTransactionLog);
		txn = ds.beginTransaction(TransactionOptions.Builder.withXG(true));
		ds.put(e1);
		ds.put(ents);
		txn.commit();

	}
	
	public void edit(InterswitchTransactionLog interswitchTransactionLog) {
		
		Entity e1 = Util
				.interswitchTransactionLogToEntity(interswitchTransactionLog);
		txn = ds.beginTransaction(TransactionOptions.Builder.withXG(true));
		ds.put(e1);
		txn.commit();

	}

	public void destroy(Key key) throws RollbackFailureException, Exception {
		ds.delete(key);
	}

	public InterswitchTransactionLog findInterswitchTransactionLog(Key key) {
		InterswitchTransactionLog itl = null;
		try {
			Entity e = ds.get(key);
			itl = Util.toInterswitchTransactionLog(e);
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return itl;
	}
	
	public List<InterswitchTransactionLog> findAll() {
		Query q = new Query(InterswitchTransactionLog.class.getSimpleName());
		PreparedQuery pq = ds.prepare(q);
		Iterator<Entity> it = pq.asIterator();
		List<InterswitchTransactionLog> list = new ArrayList<>();
		while(it.hasNext()) {
			list.add(Util.toInterswitchTransactionLog(it.next()));
		}
		return list;
	}
}
