package com.salesmaxx.persistence.controllers;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Transaction;
import com.salesmaxx.entities.ContactForm;
import com.salesmaxx.util.Util;

public class ContactFormController {

	private static DatastoreService ds = EMF.getDs();
	private Transaction txn = null;
	
	public void create(ContactForm cf) {
		
		Entity e = Util.contactFormToEntity(cf);
		txn = ds.beginTransaction();
		ds.put(e);
		txn.commit();
	}
}
