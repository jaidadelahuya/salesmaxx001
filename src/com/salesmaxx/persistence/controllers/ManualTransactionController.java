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
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
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

	public ManualTransaction findByTxnRef(String txnRef) {
		Query q = new Query(ManualTransaction.class.getSimpleName());
		Filter filter = new FilterPredicate("txnRef", FilterOperator.EQUAL,
				txnRef);
		q.setFilter(filter);
		PreparedQuery pq = ds.prepare(q);
		Entity e = pq.asSingleEntity();

		return Util.entityToManualTransaction(e);
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

	public ChequePaymentBean addManualTransactions(ChequePaymentBean cpb) {
		
		String category = cpb.getCategory();
		int fetchSize = cpb.getNoOfEntries();
		String cur = cpb.getCursor();
		
		if(fetchSize == 0) {
			fetchSize = 10;
		}
		FetchOptions fetchOptions = FetchOptions.Builder.withLimit(fetchSize);
		Cursor cursor = null;
		if(Util.notNull(cur)) {
			cursor = Cursor.fromWebSafeString(cur);
			fetchOptions.startCursor(cursor);
		}
		List<ManualTransaction> mts = new ArrayList<>();
		Query q = new Query(ManualTransaction.class.getSimpleName());
		q.addSort("issueDate", SortDirection.DESCENDING);
		if (category.equalsIgnoreCase(ChequeInvoice.InvoiceStatus.PENDING
				.name())
				| category.equalsIgnoreCase(ChequeInvoice.InvoiceStatus.CLEARED
						.name())) {
			Filter statusFilter =
					  new FilterPredicate("status",
					                      FilterOperator.EQUAL,
					                      category.toUpperCase());
			q.setFilter(statusFilter);
		}
		if (category.equalsIgnoreCase(ChequeInvoice.InvoiceStatus.OVERDUE
				.name())) {
			Filter statusFilter =
					  new FilterPredicate("issueDate",
					                      FilterOperator.LESS_THAN,
					                      new Date());
			q.setFilter(statusFilter);
		}
		if (category.equalsIgnoreCase(ManualTransaction.TransactionType.CHEQUE
				.name())
				| category.equalsIgnoreCase(ManualTransaction.TransactionType.ELECTRONIC
						.name())) {
			Filter typeFilter =
					  new FilterPredicate("transactionType",
					                      FilterOperator.EQUAL,
					                      category.toUpperCase());
			q.setFilter(typeFilter);
		}
		PreparedQuery pq = ds.prepare(q);
		QueryResultList<Entity> ents = pq.asQueryResultList(fetchOptions);
		cpb.setCursor(ents.getCursor().toWebSafeString());
		for(Entity e : ents) {
			ManualTransaction mt = Util.entityToManualTransaction(e);
			mts.add(mt);
		}
		List<ManualPaymentBean> mpb = Util.toManualPaymentBean(mts);
		cpb.setMpbs(mpb);
		return cpb;
	}
}
