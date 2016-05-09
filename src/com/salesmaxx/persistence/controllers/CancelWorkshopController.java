package com.salesmaxx.persistence.controllers;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.SortDirection;
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
}
