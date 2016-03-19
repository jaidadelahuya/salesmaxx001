package com.salesmaxx.persistence.controllers;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.salesmaxx.entities.EventTemplate;
import com.salesmaxx.entities.WorkshopTemplate;
import com.salesmaxx.entities.exception.RollbackFailureException;
import com.salesmaxx.util.Util;

public class WorkshopTemplateController {

	private static DatastoreService ds = EMF.getDs();
	private static EntityManagerFactory emf = EMF.get();
	private Transaction txn = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(WorkshopTemplate workshopTemplate)  {
		Entity ent = Util.WorkshopTemplateToEntity(workshopTemplate);
		txn = ds.beginTransaction();
		ds.put(ent);
		txn.commit();
		Util.WORKSHOP_CACHE.clearAll();
	}

	public void edit(WorkshopTemplate workshopTemplate) throws RollbackFailureException,
			Exception {
		create(workshopTemplate);
	}

	public void destroy(WorkshopTemplate workshopTemplate) throws RollbackFailureException, Exception {
		txn = ds.beginTransaction();
		ds.delete(workshopTemplate.getWorkshopId());
		txn.commit();
	}

	public WorkshopTemplate findWorkshopTemplate(Key key) {
		EntityManager em = getEntityManager();
		try {
			WorkshopTemplate a = em.find(WorkshopTemplate.class, key);
			if(a != null) {
				a.getAssessmentQuestions();
				a.getAudiences();
				a.getCourseContent();
				a.getIndustries();
				a.getLearningOutcomes();
				a.getProfessions();
				a.getRelatedWorkshops();
				a.getReviews();
				a.getSkillLevel();
				a.getDownloadables();
			}
			
			return a;
		} finally {
			em.close();
		}
	}
	
	public List<WorkshopTemplate> getAllWorkshopTemplatesKeys() {
		Query q = new Query("WorkshopTemplate");
		q.addSort("workshopName");
		PreparedQuery pq = ds.prepare(q);
		
		List<WorkshopTemplate> temps = new ArrayList<WorkshopTemplate>();
		String name = null;
		String id = null;
		for(Entity result: pq.asIterable()) {
			name =(String) result.getProperty("workshopName");
			id = result.getKey().getName();
			WorkshopTemplate wt = new WorkshopTemplate(id);
			wt.setWorkshopName(name);
			temps.add(wt);
			wt = null;
		}
		return temps;
	}
	
	public List<WorkshopTemplate> getAllWorkshops() {
		Query q = new Query("WorkshopTemplate");
		q.addSort("workshopName");
		PreparedQuery pq = ds.prepare(q);
		
		List<WorkshopTemplate> temps = new ArrayList<WorkshopTemplate>();
		for(Entity result: pq.asIterable()) {
			WorkshopTemplate wt = Util.entityToWorkshopTemplate(result);
			temps.add(wt);
		}
		return temps;
	}

	public void edit(List<WorkshopTemplate> workshopTemplates) {
		List<Entity> ents = new ArrayList<>();
		for(WorkshopTemplate wt : workshopTemplates) {
			ents.add(Util.WorkshopTemplateToEntity(wt));
		}
		txn = ds.beginTransaction();
		ds.put(ents);
		txn.commit();
		
	}
}
