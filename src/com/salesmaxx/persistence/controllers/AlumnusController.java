package com.salesmaxx.persistence.controllers;



import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Transaction;
import com.salesmaxx.entities.Alumnus;
import com.salesmaxx.util.Util;

public class AlumnusController {

	private static DatastoreService ds = EMF.getDs();
	private Transaction txn = null;
	
	public void create(Alumnus alumnus) {
		
		Entity ent = Util.alunmusToEntity(alumnus);
		txn = ds.beginTransaction();
		ds.put(ent);
		txn.commit();
	}
}
