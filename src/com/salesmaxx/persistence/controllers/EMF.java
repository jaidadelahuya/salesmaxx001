package com.salesmaxx.persistence.controllers;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;

public class EMF {

	private static final EntityManagerFactory emfInstance = Persistence
			.createEntityManagerFactory("transactions-optional");
	private static final DatastoreService ds = DatastoreServiceFactory
			.getDatastoreService();

	public static DatastoreService getDs() {
		return ds;
	}

	public static EntityManagerFactory get() {
		return emfInstance;
	}
}
