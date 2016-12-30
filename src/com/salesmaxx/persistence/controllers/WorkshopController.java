package com.salesmaxx.persistence.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyRange;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.datastore.TransactionOptions;
import com.salesmaxx.entities.Address;
import com.salesmaxx.entities.WorkShop;
import com.salesmaxx.entities.WorkshopTemplate;
import com.salesmaxx.entities.exception.RollbackFailureException;
import com.salesmaxx.util.Util;

public class WorkshopController {

	private static DatastoreService ds = EMF.getDs();
	private static EntityManagerFactory emf = EMF.get();
	private Transaction txn = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(WorkShop workShop, Address address,
			WorkshopTemplate template) {
	
		List<Key> schedules = template.getSchedules();
		if (schedules == null) {
			schedules = new ArrayList<>();
		}
		Entity ent3 = Util.WorkshopTemplateToEntity(template);
		Entity ent = Util.workshopToEntity(workShop);
		schedules.add(ent.getKey());
		ent3.setUnindexedProperty("schedules", schedules);
		Entity ent2 = Util.addressToEntity(address, workShop.getId());
		ent.setUnindexedProperty("location", ent2.getKey());
		txn = ds.beginTransaction(TransactionOptions.Builder.withXG(true));
		ds.put(ent);
		ds.put(ent3);
		ds.put(ent2);
		txn.commit();
		Util.WORKSHOP_CACHE.clearAll();

	}
	
	

	public void destroy(Key key) throws RollbackFailureException, Exception {
		EntityManager em = null;
		try {
			txn = ds.beginTransaction();
			em = getEntityManager();
			WorkShop workShop;
			workShop = em.getReference(WorkShop.class, key);
			em.remove(workShop);
			txn.commit();
		} catch (Exception ex) {
			try {
				txn.rollback();
			} catch (Exception re) {
				throw new RollbackFailureException(
						"An error occurred attempting to roll back the transaction.",
						re);
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public WorkShop findWorkShop(Key key) {
		Entity ent;
		try {
			ent = ds.get(key);
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return new WorkShop();

	}

	public List<WorkShop> getAllWorkshops() {
		Query q = new Query(WorkShop.class.getSimpleName());
		q.addSort("startDate");
		PreparedQuery pq = ds.prepare(q);
		List<WorkShop> temps = new ArrayList<WorkShop>();
		for (Entity result : pq.asIterable()) {
			WorkShop wt = Util.entityToWorkshop(result);
			temps.add(wt);
		}
		return temps;
	}

	public List<WorkShop> getWorkshops(Date start, Date end) {
		Query q = new Query(WorkShop.class.getSimpleName());
		q.addSort("startDate");
		Filter f1 = new Query.FilterPredicate("startDate",
				FilterOperator.GREATER_THAN, start);
		Filter f2 = new Query.FilterPredicate("startDate",
				FilterOperator.LESS_THAN, end);
		List<Filter> filters = new ArrayList<>();
		filters.add(f1);
		filters.add(f2);
		Filter f3 = new Query.CompositeFilter(CompositeFilterOperator.AND,
				filters);
		q.setFilter(f3);
		q.setKeysOnly();
		PreparedQuery pq = ds.prepare(q);
		Iterator<Entity> ents = pq.asIterator();
		List<WorkShop> workshops = new ArrayList<>();
		while (ents.hasNext()) {
			workshops.add(Util.getWorkshopSchedule(String.valueOf(ents.next()
					.getKey().getId())));
		}

		return workshops;
	}

	public void edit(List<WorkShop> schedules) {
		List<Entity> ents = new ArrayList<>();
		for (WorkShop w : schedules) {
			
			ents.add(Util.workshopToEntity(w));
			Util.WORKSHOP_CACHE.put(w.getId(), w);
		}
		txn = ds.beginTransaction();
		ds.put(ents);
		txn.commit();
		

	}
}
