package com.salesmaxx.persistence.controllers;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.datastore.QueryResultList;
import com.google.appengine.api.datastore.Transaction;
import com.salesmaxx.entities.CanceledWorkshop;
import com.salesmaxx.util.Util;

public class CancelWorkshopController {

	private static DatastoreService ds = EMF.getDs();
	private Transaction txn = null;

	public List<CanceledWorkshop> findCancelWorkshopByWorkshopId(String workshopId) {
		List<CanceledWorkshop> cws = new ArrayList<>();
		Query q = new Query(CanceledWorkshop.class.getSimpleName());
		Filter f = new Query.FilterPredicate("workshopScheduleId", FilterOperator.EQUAL, workshopId);
		q.setFilter(f);
		q.addSort("cancelDate", SortDirection.DESCENDING);
		PreparedQuery pq = ds.prepare(q);
		Iterable<Entity> its = pq.asIterable();
		for(Entity e : its) {
			cws.add(Util.entityToCancelWorkshop(e));
		}
		return cws;
	}

	public QueryResultList<Entity> findByStatus(String status, String cursor) {
		return findByStatus(status, cursor, null);
	}

	public QueryResultList<Entity> findByStatus(String status, String cursor, String uid) {
		Boolean b = false;
		if(status.equalsIgnoreCase("cleared")) {
			b = true;
		}
		
		Query q = new Query(CanceledWorkshop.class.getSimpleName());
		Filter f = new Query.FilterPredicate("cleared", FilterOperator.EQUAL, b);
		Filter f1 = null;
		if(Util.notNull(uid)) {
			f1 = new Query.FilterPredicate("owner", FilterOperator.EQUAL, uid.trim().toUpperCase());
			List<Filter> l = new ArrayList<>();
			l.add(f); l.add(f1);
			Filter f2 = new Query.CompositeFilter(CompositeFilterOperator.AND, l);
			q.setFilter(f2);
			cursor = null;
		}else {
			q.setFilter(f);
		}
		
		
		PreparedQuery pq = ds.prepare(q);
		FetchOptions fo = null;
		if(cursor != null) {
			Cursor c = Cursor.fromWebSafeString(cursor);
			fo = FetchOptions.Builder.withStartCursor(c);
		}else {
			fo = FetchOptions.Builder.withDefaults();
		}
		QueryResultList<Entity> e = pq.asQueryResultList(fo);
		
		return e;
	}

	public CanceledWorkshop findByKey(Key key) {
		try {
			Entity e = EMF.getDs().get(key);
			return Util.entityToCancelWorkshop(e);
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	

	
}
