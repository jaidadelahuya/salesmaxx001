package com.salesmaxx.persistence.controllers;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;
import com.salesmaxx.entities.MailingList;
import com.salesmaxx.util.Util;

public class MailingListController {

	private static DatastoreService ds = EMF.getDs();
	private Transaction txn = null;
	
	public void create(MailingList ml) {
		Entity e = Util.mailingListToEntity(ml);
		txn = ds.beginTransaction();
		ds.put(e);
		txn.commit();
	}
	
	public void destroy (MailingList ml) {
		Key key = KeyFactory.createKey(MailingList.class.getSimpleName(), ml.getEmail());
		txn = ds.beginTransaction();
		ds.delete(key);
		txn.commit();
	}
	
	public List<MailingList> findAll() {
		Query q = new Query(MailingList.class.getSimpleName());
		PreparedQuery pq = ds.prepare(q);
		Iterator<Entity> entities = pq.asIterator();
		List<MailingList> list = new ArrayList<MailingList>();
		while(entities.hasNext()) {
			MailingList ml = Util.entityToMailingList(entities.next());
			list.add(ml);
		}
		return list;
	}
}
