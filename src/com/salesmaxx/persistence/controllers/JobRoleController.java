package com.salesmaxx.persistence.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;
import com.salesmaxx.entities.JobRole;
import com.salesmaxx.entities.exception.RollbackFailureException;

public class JobRoleController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7631309941550587808L;
	private static DatastoreService ds = EMF.getDs();
	private Transaction txn = null;

	public void create(JobRole jobRole) {
		Entity ent = new Entity("JobRole",jobRole.getName().getName());
		txn = ds.beginTransaction();
		ds.put(ent);
		txn.commit();
	}

	public void edit(JobRole jobRole)
			throws RollbackFailureException, Exception {
		create(jobRole);
	}

	public void destroy(JobRole jobRole)
			throws RollbackFailureException, Exception {
		txn = ds.beginTransaction();
		ds.delete(jobRole.getName());
		txn.commit();
	}

	public List<JobRole> getAllJobRoles() {
		Query q = new Query("JobRole");
		PreparedQuery pq = ds.prepare(q);

		List<JobRole> temps = new ArrayList<JobRole>();
		for (Entity result : pq.asIterable()) {
			JobRole wt = new JobRole((String) result.getKey().getName());
			temps.add(wt);
		}
		return temps;
	}

}
