package com.salesmaxx.persistence.controllers;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManagerFactory;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.datastore.TransactionOptions;
import com.salesmaxx.entities.PurchaseableItem;
import com.salesmaxx.util.Util;

public class PurchaseableItemController {

	private static DatastoreService ds = EMF.getDs();
	private static EntityManagerFactory emf = EMF.get();
	private Transaction txn = null;
	
	public boolean create(List<Entity> ents) {
		txn = ds.beginTransaction(TransactionOptions.Builder.withXG(true));
		ds.put(ents);
		txn.commit();
		return true;
		
	}

	public List<PurchaseableItem> findAll(List<Key> list) {
		Map<Key, Entity> map = ds.get(list);
		List<PurchaseableItem> l = Util.toPurchaseableItems(map);
		return l;
	}
}
