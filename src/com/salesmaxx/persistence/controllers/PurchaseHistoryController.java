package com.salesmaxx.persistence.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;
import com.salesmaxx.entities.PurchaseHistory;
import com.salesmaxx.entities.exception.RollbackFailureException;
import com.salesmaxx.util.Util;

public class PurchaseHistoryController {

	private static DatastoreService ds = EMF.getDs();
	private static EntityManagerFactory emf = EMF.get();
	private Transaction txn = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(PurchaseHistory purchaseHistory) throws RollbackFailureException,
			Exception {
		EntityManager em = null;
		try {
			txn = ds.beginTransaction();
			em = getEntityManager();
			em.persist(purchaseHistory);
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

	public void edit(PurchaseHistory purchaseHistory) throws RollbackFailureException,
			Exception {
		create(purchaseHistory);
	}

	public void destroy(String id) throws RollbackFailureException, Exception {
		EntityManager em = null;
		try {
			txn = ds.beginTransaction();
			em = getEntityManager();
			PurchaseHistory purchaseHistory;
			purchaseHistory = em.getReference(PurchaseHistory.class, id);
			em.remove(purchaseHistory);
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

	public PurchaseHistory findPurchaseHistory(String id) {
		EntityManager em = getEntityManager();
		try {
			PurchaseHistory a = em.find(PurchaseHistory.class, id);
			return a;
		} finally {
			em.close();
		}
	}

	public List<PurchaseHistory> find(List<Key> phKeys) {
		Map<Key,Entity> map = ds.get(phKeys);
		Set<Key> keys  = map.keySet();
		List<PurchaseHistory> phs = new ArrayList<PurchaseHistory>();
		for(Key k : keys) {
			Entity e = map.get(k);
			PurchaseHistory ph = Util.entityToPurchaseHistory(e);
			phs.add(ph);
		}
		return phs;
	}

	public PurchaseHistory findByTransactionRef(String txn) {
		PurchaseHistory ph = null;
		Query q = new Query(PurchaseHistory.class.getSimpleName());
		q.setFilter(new Query.FilterPredicate("txnRef",
				Query.FilterOperator.EQUAL, txn));
		PreparedQuery pq = ds.prepare(q);
		Entity e = pq.asSingleEntity();
		if(e == null) {
			return null;
		} else {
			ph = Util.entityToPurchaseHistory(e);
			return ph;
		}
		

	}
}
