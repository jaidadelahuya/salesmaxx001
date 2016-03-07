package com.salesmaxx.persistence.controllers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Transaction;
import com.salesmaxx.entities.GiftItem;
import com.salesmaxx.entities.exception.RollbackFailureException;

public class GiftItemController {

	private static DatastoreService ds = EMF.getDs();
	private static EntityManagerFactory emf = EMF.get();
	private Transaction txn = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(GiftItem giftItem) throws RollbackFailureException,
			Exception {
		EntityManager em = null;
		try {
			txn = ds.beginTransaction();
			em = getEntityManager();
			em.persist(giftItem);
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

	public void edit(GiftItem giftItem) throws RollbackFailureException,
			Exception {
		create(giftItem);
	}

	public void destroy(String id) throws RollbackFailureException, Exception {
		EntityManager em = null;
		try {
			txn = ds.beginTransaction();
			em = getEntityManager();
			GiftItem giftItem;
			giftItem = em.getReference(GiftItem.class, id);
			em.remove(giftItem);
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

	public GiftItem findGiftItem(String id) {
		EntityManager em = getEntityManager();
		try {
			GiftItem a = em.find(GiftItem.class, id);
			return a;
		} finally {
			em.close();
		}
	}

}
