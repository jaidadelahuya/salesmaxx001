package com.salesmaxx.persistence.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.datastore.TransactionOptions;
import com.google.apphosting.utils.config.AppEngineWebXml.UseGoogleConnectorJ;
import com.salesmaxx.entities.PurchaseHistory;
import com.salesmaxx.entities.User;
import com.salesmaxx.entities.UserGeneralInfo;
import com.salesmaxx.entities.exception.RollbackFailureException;
import com.salesmaxx.util.Util;

public class UserGeneralInfoController {

	private static DatastoreService ds = EMF.getDs();
	private static EntityManagerFactory emf = EMF.get();
	private Transaction txn = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
	public void edit(UserGeneralInfo userGeneralInfo, Key userKey) {
	
		Entity ent = Util.UserGeneralInfoToEntity(userGeneralInfo, userKey);
		txn = ds.beginTransaction(TransactionOptions.Builder.withXG(true));
		ds.put(ent);
		txn.commit();
	}

	public void edit(UserGeneralInfo userGeneralInfo, Key userKey, List<PurchaseHistory> phs) {
		List<Entity> phEnts = Util.purchaseHistoryToEntity(phs);
		List<Key> phKeys = userGeneralInfo.getPurchaseHistory();
		if(phKeys == null) {
			phKeys = new ArrayList<Key>();
		}
		for(Entity e: phEnts) {
			phKeys.add(e.getKey());
		}
		userGeneralInfo.setPurchaseHistory(phKeys);
		Entity ent = Util.UserGeneralInfoToEntity(userGeneralInfo, userKey);
		txn = ds.beginTransaction(TransactionOptions.Builder.withXG(true));
		ds.put(ent);
		ds.put(phEnts);
		txn.commit();
	}

	public void destroy(long id) throws RollbackFailureException, Exception {
		EntityManager em = null;
		try {
			txn = ds.beginTransaction();
			em = getEntityManager();
			UserGeneralInfo userGeneralInfo;
			userGeneralInfo = em.getReference(UserGeneralInfo.class, id);
			em.remove(userGeneralInfo);
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

	public UserGeneralInfo findUserGeneralInfo(User user,long id) {
		Key key = KeyFactory.createKey(user.getRegId(), UserGeneralInfo.class.getSimpleName(), id);
		Entity e = null;
		try {
			e = ds.get(key);
		} catch (EntityNotFoundException enfe) {
			return null;
		}
		
		return Util.EntityToUserGeneralInfo(e);
	}

}
