package com.salesmaxx.persistence.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilter;
import com.google.appengine.api.datastore.TransactionOptions;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.datastore.QueryResultList;
import com.google.appengine.api.datastore.Transaction;
import com.salesmaxx.beans.ChequeInvoice;
import com.salesmaxx.beans.ChequePaymentBean;
import com.salesmaxx.beans.ManualPaymentBean;
import com.salesmaxx.entities.ManualTransaction;
import com.salesmaxx.entities.User;
import com.salesmaxx.util.Util;

public class ManualTransactionController {

	private static DatastoreService ds = EMF.getDs();
	private Transaction txn = null;

	public void create(ManualTransaction mt) {
		Entity e = Util.ManualTransactionToEntity(mt);
		txn = ds.beginTransaction();
		ds.put(e);
		txn.commit();

	}

	public void create(List<ManualTransaction> mts) {
		List<Entity> ents = new ArrayList<>();
		for (ManualTransaction mt : mts) {
			Entity e = Util.ManualTransactionToEntity(mt);
			ents.add(e);
		}
		txn = ds.beginTransaction(TransactionOptions.Builder.withXG(true));
		ds.put(ents);
		txn.commit();

	}

	public List<ManualTransaction> findByTxnRef(String txnRef,
			ChequeInvoice.InvoiceStatus status) {
		Query q = new Query(ManualTransaction.class.getSimpleName());
		Filter filter = new FilterPredicate("txnRef", FilterOperator.EQUAL,
				txnRef);
		Filter f2 = null;
		switch (status) {
		case CLEARED:
			f2 = new FilterPredicate("status", FilterOperator.EQUAL,
					ChequeInvoice.InvoiceStatus.CLEARED.name());
			break;
		case OVERDUE:
			f2 = new FilterPredicate("status", FilterOperator.EQUAL,
					ChequeInvoice.InvoiceStatus.OVERDUE.name());
			break;
		case PENDING:
			f2 = new FilterPredicate("status", FilterOperator.EQUAL,
					ChequeInvoice.InvoiceStatus.PENDING.name());

		}

		if (f2 == null) {
			q.setFilter(filter);
		} else {
			q.setFilter(CompositeFilterOperator.and(filter, f2));
		}

		PreparedQuery pq = ds.prepare(q);
		Iterable<Entity> e = pq.asIterable();
		List<ManualTransaction> list = new ArrayList<>();
		for (Entity ee : e) {
			list.add(Util.entityToManualTransaction(ee));
		}
		return list;
	}

	public List<ManualTransaction> find(List<Key> mtKeys) {
		Map<Key, Entity> ents = ds.get(mtKeys);
		List<ManualTransaction> l = new ArrayList<>();
		for (Key k : ents.keySet()) {
			Entity e = ents.get(k);
			ManualTransaction mt = Util.entityToManualTransaction(e);
			l.add(mt);
		}
		return l;
	}

	public QueryResultList<Entity> addManualTransactions(String category,
			String cursor) {

		int fetchSize = 10;
		FetchOptions fetchOptions = FetchOptions.Builder.withLimit(fetchSize);
		Cursor c = null;
		if (Util.notNull(cursor)) {
			c = Cursor.fromWebSafeString(cursor);
			fetchOptions.startCursor(c);
		}
		
		Query q = new Query(ManualTransaction.class.getSimpleName());
		q.addSort("issueDate", SortDirection.DESCENDING);
		if (category.equalsIgnoreCase(ChequeInvoice.InvoiceStatus.PENDING
				.name())
				| category.equalsIgnoreCase(ChequeInvoice.InvoiceStatus.CLEARED
						.name())) {
			Filter statusFilter = new FilterPredicate("status",
					FilterOperator.EQUAL, category.toUpperCase());
			q.setFilter(statusFilter);
		} else if (category
				.equalsIgnoreCase(ChequeInvoice.InvoiceStatus.OVERDUE.name())) {
			Filter statusFilter = new FilterPredicate("issueDate",
					FilterOperator.LESS_THAN, new Date());
			q.setFilter(statusFilter);
		}

		PreparedQuery pq = ds.prepare(q);
		QueryResultList<Entity> ents = pq.asQueryResultList(fetchOptions);
		return ents;
		
		
		
	}

	public static QueryResultList<Entity> getChequePaymentBean(String txnID,
			String category, String uid) {
		Query q = new Query(ManualTransaction.class.getSimpleName());
		List<Filter> f = new ArrayList<>();
		Filter statusFilter = new FilterPredicate("status",
				FilterOperator.EQUAL, category.toUpperCase());
		f.add(statusFilter);
		if(Util.notNull(txnID)) {
			Filter txnFilter = new FilterPredicate("txnRef",
					FilterOperator.EQUAL, txnID.toUpperCase());
			f.add(txnFilter);
		}
		if(Util.notNull(uid)) {
			Filter oFilter = new FilterPredicate("ownerKey",
					FilterOperator.EQUAL, KeyFactory.createKey(User.class.getSimpleName(), uid));
			f.add(oFilter);
		}
		
		if(f.size() > 1) {
			q.setFilter(new CompositeFilter(CompositeFilterOperator.AND, f));
		}else {
			q.setFilter(statusFilter);
		}
		q.addSort("issueDate");
		PreparedQuery pq = ds.prepare(q);
		QueryResultList<Entity> ents = pq.asQueryResultList(FetchOptions.Builder.withLimit(50));
		return ents;
	}
}
