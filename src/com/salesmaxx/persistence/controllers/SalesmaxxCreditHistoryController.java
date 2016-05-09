package com.salesmaxx.persistence.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.salesmaxx.entities.SalesmaxxCreditHistory;
import com.salesmaxx.entities.exception.RollbackFailureException;
import com.salesmaxx.util.Util;

public class SalesmaxxCreditHistoryController {

	private static DatastoreService ds = EMF.getDs();
	private static EntityManagerFactory emf = EMF.get();
	private Transaction txn = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(SalesmaxxCreditHistory salesmaxxCreditHistory) throws RollbackFailureException,
			Exception {
		EntityManager em = null;
		try {
			txn = ds.beginTransaction();
			em = getEntityManager();
			em.persist(salesmaxxCreditHistory);
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

	public void edit(SalesmaxxCreditHistory salesmaxxCreditHistory) throws RollbackFailureException,
			Exception {
		create(salesmaxxCreditHistory);
	}

	public void destroy(String id) throws RollbackFailureException, Exception {
		EntityManager em = null;
		try {
			txn = ds.beginTransaction();
			em = getEntityManager();
			SalesmaxxCreditHistory salesmaxxCreditHistory;
			salesmaxxCreditHistory = em.getReference(SalesmaxxCreditHistory.class, id);
			em.remove(salesmaxxCreditHistory);
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

	public SalesmaxxCreditHistory findSalesmaxxCreditHistory(String id) {
		EntityManager em = getEntityManager();
		try {
			SalesmaxxCreditHistory a = em.find(SalesmaxxCreditHistory.class, id);
			return a;
		} finally {
			em.close();
		}
	}

	public List<SalesmaxxCreditHistory> findALL(
			List<Key> salesmaxxHistoryCredits) {
		Map<Key,Entity> e = ds.get(salesmaxxHistoryCredits);
		List<SalesmaxxCreditHistory> l = new ArrayList<>();
		Set<Key> keys = e.keySet();
		for(Key k : keys) {
			SalesmaxxCreditHistory s = Util.entityToSalesMaxxCreditHistory(e.get(k));
			l.add(s);
			Collections.sort(l);
		}
		return l;
	}
}
