package com.salesmaxx.persistence.controllers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Transaction;
import com.salesmaxx.entities.SocialNetwork;
import com.salesmaxx.entities.exception.RollbackFailureException;

public class SocialNetworkController {

	private static DatastoreService ds = EMF.getDs();
	private static EntityManagerFactory emf = EMF.get();
	private Transaction txn = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(SocialNetwork socialNetwork) throws RollbackFailureException,
			Exception {
		EntityManager em = null;
		try {
			txn = ds.beginTransaction();
			em = getEntityManager();
			em.persist(socialNetwork);
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

	public void edit(SocialNetwork socialNetwork) throws RollbackFailureException,
			Exception {
		create(socialNetwork);
	}

	public void destroy(String id) throws RollbackFailureException, Exception {
		EntityManager em = null;
		try {
			txn = ds.beginTransaction();
			em = getEntityManager();
			SocialNetwork socialNetwork;
			socialNetwork = em.getReference(SocialNetwork.class, id);
			em.remove(socialNetwork);
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

	public SocialNetwork findSocialNetwork(String id) {
		EntityManager em = getEntityManager();
		try {
			SocialNetwork a = em.find(SocialNetwork.class, id);
			return a;
		} finally {
			em.close();
		}
	}
}
