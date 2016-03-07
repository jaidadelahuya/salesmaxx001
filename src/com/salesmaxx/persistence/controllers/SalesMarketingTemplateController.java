package com.salesmaxx.persistence.controllers;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;
import com.salesmaxx.entities.SalesMarketingTemplate;
import com.salesmaxx.util.Util;

public class SalesMarketingTemplateController {

	private static DatastoreService ds = EMF.getDs();
	private Transaction txn = null;
	
	public void create(SalesMarketingTemplate smt) {
		Entity e = Util.SalesMarketingTemplateToEntity(smt);
		txn = ds.beginTransaction();
		ds.put(e);
		txn.commit();
	}
	
	public SalesMarketingTemplate find(Key key) {
		Entity e;
		try {
			e = ds.get(key);
		} catch (EntityNotFoundException e1) {
			return null;
		}
		return Util.entityToSalesMarketingTemplate(e);
		
	}
}
