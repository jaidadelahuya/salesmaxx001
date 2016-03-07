package com.salesmaxx.persistence.controllers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.salesmaxx.entities.Cart;
import com.salesmaxx.entities.User;
import com.salesmaxx.entities.exception.RollbackFailureException;
import com.salesmaxx.util.Util;

public class CartController {

	private static DatastoreService ds = EMF.getDs();
	private static EntityManagerFactory emf = EMF.get();
	private Transaction txn = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(Cart cart) throws RollbackFailureException,
			Exception {
		EntityManager em = null;
		try {
			txn = ds.beginTransaction();
			em = getEntityManager();
			em.persist(cart);
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

	public void edit(Cart cart) {
		Entity e = Util.cartToEntity(cart);
		txn = ds.beginTransaction();
		ds.put(e);
		txn.commit();
	}

	public void destroy(Key key) throws RollbackFailureException, Exception {
		EntityManager em = null;
		try {
			txn = ds.beginTransaction();
			em = getEntityManager();
			Cart cart;
			cart = em.getReference(Cart.class, key);
			em.remove(cart);
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

	public Cart findCart(Key key) {
		
		if(key == null) {
			return new Cart();
		} else {
			Cart c = null;
			try {
				Entity e = ds.get(key);
				c = Util.toCart(e);
			} catch (EntityNotFoundException e) {
				e.printStackTrace();
				return c;
			}
			return c;
		}
		
	}

}

