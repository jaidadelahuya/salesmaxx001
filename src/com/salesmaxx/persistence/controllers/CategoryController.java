package com.salesmaxx.persistence.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;
import com.salesmaxx.entities.Category;
import com.salesmaxx.entities.WorkshopTemplate;
import com.salesmaxx.entities.exception.RollbackFailureException;
import com.salesmaxx.util.Util;

public class CategoryController {

	private static DatastoreService ds = EMF.getDs();
	private static EntityManagerFactory emf = EMF.get();
	private Transaction txn = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(Category category) throws RollbackFailureException,
			Exception {
		EntityManager em = null;
		try {
			txn = ds.beginTransaction();
			em = getEntityManager();
			em.persist(category);
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

	public void edit(Category category) throws RollbackFailureException,
			Exception {
		create(category);
	}

	public void destroy(Key key) throws RollbackFailureException, Exception {
		EntityManager em = null;
		try {
			txn = ds.beginTransaction();
			em = getEntityManager();
			Category category;
			category = em.getReference(Category.class, key);
			em.remove(category);
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

	public Category findCategory(Key key) {
		EntityManager em = getEntityManager();
		try {
			Category a = em.find(Category.class, key);
			return a;
		} finally {
			em.close();
		}
	}

	public boolean categoryIdExists(String categoryId) {
		Query q = new Query("Category");
		q.setFilter(new Query.FilterPredicate("categoryId",
				Query.FilterOperator.EQUAL, categoryId));
		PreparedQuery pq = ds.prepare(q);
		int i = pq.countEntities(FetchOptions.Builder.withLimit(1));
		if(i== 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public List<Category> getAllCategories() {
		List<Category> categories = new ArrayList<Category>();
		Query q = new Query("Category");
		PreparedQuery pq = ds.prepare(q);
		for(Entity result: pq.asIterable()) {
			Category cat = new Category(result.getKey().getName());
			cat.setCatalogueLink((String) result.getProperty("catalogueLink"));
			cat.setCategoryId((String) result.getProperty("categoryId"));
			cat.setImage((BlobKey) result.getProperty("image"));
			cat.setWorkshops((List<String>) result.getProperty("workshops"));
			categories.add(cat);
		}
		return categories;
	}
}
