package com.salesmaxx.persistence.controllers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;
import com.salesmaxx.entities.LoyaltyOrganisation;
import com.salesmaxx.entities.exception.RollbackFailureException;

public class LoyaltyOrganisationController {

	private static DatastoreService ds = EMF.getDs();
	private static EntityManagerFactory emf = EMF.get();
	private Transaction txn = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(LoyaltyOrganisation loyaltyOrganisation) throws RollbackFailureException,
			Exception {
		EntityManager em = null;
		try {
			txn = ds.beginTransaction();
			em = getEntityManager();
			em.persist(loyaltyOrganisation);
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

	public void edit(LoyaltyOrganisation loyaltyOrganisation) throws RollbackFailureException,
			Exception {
		create(loyaltyOrganisation);
	}

	public void destroy(Key key) throws RollbackFailureException, Exception {
		EntityManager em = null;
		try {
			txn = ds.beginTransaction();
			em = getEntityManager();
			LoyaltyOrganisation loyaltyOrganisation;
			loyaltyOrganisation = em.getReference(LoyaltyOrganisation.class, key);
			em.remove(loyaltyOrganisation);
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

	public LoyaltyOrganisation findLoyaltyOrganisation(Key key) {
		EntityManager em = getEntityManager();
		try {
			LoyaltyOrganisation a = em.find(LoyaltyOrganisation.class, key);
			return a;
		} finally {
			em.close();
		}
	}
	
	public boolean organizationIdExists(String organizationId) {
		Query q = new Query("Category");
		q.setFilter(new Query.FilterPredicate("categoryId",
				Query.FilterOperator.EQUAL, organizationId));
		PreparedQuery pq = ds.prepare(q);
		int i = pq.countEntities(FetchOptions.Builder.withLimit(1));
		if(i== 0) {
			return false;
		} else {
			return true;
		}
	}

}
