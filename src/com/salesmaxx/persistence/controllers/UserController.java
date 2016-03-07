package com.salesmaxx.persistence.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyRange;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.datastore.TransactionOptions;
import com.salesmaxx.beans.SocialUser;
import com.salesmaxx.entities.Address;
import com.salesmaxx.entities.Cart;
import com.salesmaxx.entities.User;
import com.salesmaxx.entities.UserGeneralInfo;
import com.salesmaxx.entities.exception.RollbackFailureException;
import com.salesmaxx.util.Util;

public class UserController {

	private static DatastoreService ds = EMF.getDs();
	private static EntityManagerFactory emf = EMF.get();
	private Transaction txn = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public boolean create(User user) {

		boolean exists = userExists(user.getUsername());
		if (exists) {
			return false;
		} else {
			exists = userExists(user.getRegId());
			if (exists) {
				return false;
			} else {
				KeyRange range = ds.allocateIds("UserGeneralInfo", 1);
				Key key = range.getStart();
				user.setGeneralInfoId(key.getId());
				Entity ent = Util.UserToEntity(user);
				Entity ent1 = new Entity("UserGeneralInfo", key.getId(),
						ent.getKey());
				txn = ds.beginTransaction();
				ds.put(ent);
				ds.put(ent1);
				txn.commit();
				Util.USER_CACHE.clearAll();
				return true;
			}
		}
	}

	public boolean create(User user, UserGeneralInfo ugi) {

		boolean exists = userExists(user.getUsername());
		if (exists) {
			return false;
		} else {
			exists = userExists(user.getRegId());
			if (exists) {
				return false;
			} else {
				KeyRange range = ds.allocateIds("UserGeneralInfo", 1);
				Key key = range.getStart();
				user.setGeneralInfoId(key.getId());
				ugi.setId(key.getId());
				Entity ent = Util.UserToEntity(user);
				Entity ent1 = Util
						.UserGeneralInfoToEntity(ugi, user.getRegId());
				txn = ds.beginTransaction();
				ds.put(ent);
				ds.put(ent1);
				txn.commit();
				Util.USER_CACHE.clearAll();
				return true;
			}
		}
	}
	



	public void edit(User user, Cart cart) {

		if (cart.getCartKey() == null) {
			KeyRange range = ds.allocateIds("Cart", 1);
			Key key = range.getStart();
			cart.setCartKey(key);
		}
		user.setCart(cart.getCartKey());
		Entity ent = Util.UserToEntity(user);
		Entity ent1 = Util.cartToEntity(cart);
		txn = ds.beginTransaction(TransactionOptions.Builder.withXG(true));
		ds.put(ent);
		ds.put(ent1);
		txn.commit();
		Util.USER_CACHE.clearAll();
	}

	public void edit(User user, UserGeneralInfo ugi, Address a) {

		Entity ugiEnt = Util.UserGeneralInfoToEntity(ugi, user.getRegId());
		Entity address = Util.addressToEntity(a, ugiEnt.getKey());
		Entity userEnt = Util.UserToEntity(user);
		ugiEnt.setUnindexedProperty("address", address.getKey());
		txn = ds.beginTransaction(TransactionOptions.Builder.withXG(true));
		ds.put(userEnt);
		ds.put(ugiEnt);
		ds.put(address);
		txn.commit();

	}

	public void edit(User user) {
		Entity ent = Util.UserToEntity(user);
		txn = ds.beginTransaction();
		ds.put(ent);
		txn.commit();
		Util.USER_CACHE.clearAll();
	}

	public void destroy(Key id) throws RollbackFailureException, Exception {
		txn = ds.beginTransaction();
		ds.delete(id);
		txn.commit();
		Util.USER_CACHE.clearAll();
	}

	public User findUser(Key regId) {
		User u = null;
		try {
			Entity e = ds.get(regId);
			u = Util.toUser(e);
		} catch (EntityNotFoundException e) {

			e.printStackTrace();
			return u;
		}
		return u;
	}

	public User findUserByUsername(String username, String password) {

		Query q = new Query("User");
		q.setFilter(new Query.FilterPredicate("username",
				Query.FilterOperator.EQUAL, username));
		PreparedQuery pq = ds.prepare(q);
		Entity e = pq.asSingleEntity();
		if (e == null) {
			return null;
		} else {
			if (password.equals(e.getProperty("password"))) {
				return Util.toUser(e);
			} else {
				return null;
			}
		}
	}

	public User findUserByUsername(String username) {

		Query q = new Query("User");
		q.setFilter(new Query.FilterPredicate("username",
				Query.FilterOperator.EQUAL, username));
		PreparedQuery pq = ds.prepare(q);
		Entity e = pq.asSingleEntity();
		if (e == null) {
			return null;
		} else {
			return Util.toUser(e);
		}
	}

	public boolean userExists(String username) {

		Query q = new Query("User");
		q.setKeysOnly();
		q.setFilter(new Query.FilterPredicate("username",
				Query.FilterOperator.EQUAL, username));
		PreparedQuery pq = ds.prepare(q);
		Entity e = pq.asSingleEntity();
		if (e == null) {
			return false;
		} else {
			return true;
		}
	}

	public boolean userExists(Key key) {

		try {
			Entity ent = ds.get(key);

		} catch (EntityNotFoundException e) {
			return false;
		}

		return true;
	}

	public User findUserByOpenId(SocialUser su) {
		Query q = new Query(User.class.getSimpleName());
		
		switch (su.getNetwork()) {
		case LINKEDIN:
			q.setFilter(new Query.FilterPredicate("linkedInId", Query.FilterOperator.EQUAL, su.getId()));
			break;

		case FACEBOOK:
			q.setFilter(new Query.FilterPredicate("facebookId", Query.FilterOperator.EQUAL, su.getId()));
			break;
		case TWITTER:
			q.setFilter(new Query.FilterPredicate("twitterId", Query.FilterOperator.EQUAL, su.getId()));
			break;
		case GOOGLE:
			q.setFilter(new Query.FilterPredicate("googleId", Query.FilterOperator.EQUAL, su.getId()));
			break;
		}
		
		PreparedQuery pq = ds.prepare(q);
		Entity e = pq.asSingleEntity();
		
		if(e == null) {
			return null;
		} else {
			return Util.toUser(e);
		}
	}

	public Map<Key, User> findUsers(List<Key> keys) {
		Map<Key,User> map = new HashMap<Key, User>();
		Map<Key,Entity> m = ds.get(keys);
		Set<Key> set = m.keySet();
		for(Key k:keys) {
			User u = Util.toUser(m.get(k));
			map.put(k, u);
		}
		return map;
	}
}
