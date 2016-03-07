package com.salesmaxx.persistence.controllers;

import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.datastore.Transaction;
import com.salesmaxx.entities.Testimonial;
import com.salesmaxx.util.Util;

public class TestimonialController {

	private static DatastoreService ds = EMF.getDs();
	private Transaction txn = null;

	public void create(Testimonial t) {
		Entity e = Util.testimonialToEntity(t);
		txn = ds.beginTransaction();
		ds.put(e);
		txn.commit();
		Util.TestimonialCache.clearAll();
	}

	public List<Testimonial> findTestimonialByLatestDate(int number) {
		List<Testimonial> l = null;
		Query q = new Query(Testimonial.class.getSimpleName());
		q.addSort("date", SortDirection.DESCENDING);
		PreparedQuery pq = ds.prepare(q);
		List<Entity> li =  pq.asList(FetchOptions.Builder.withLimit(number));
		for(Entity e : li) {
			l.add(Util.entityToTestimonial(e));
		}
		return l;
	}
}
