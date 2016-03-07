package com.salesmaxx.persistence.controllers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Transaction;
import com.salesmaxx.entities.AssessmentQuestion;
import com.salesmaxx.entities.exception.RollbackFailureException;

public class AssessmentQuestionController {

	private static DatastoreService ds = EMF.getDs();
	private static EntityManagerFactory emf = EMF.get();
	private Transaction txn = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(AssessmentQuestion assessmentQuestion) throws RollbackFailureException,
			Exception {
		EntityManager em = null;
		try {
			txn = ds.beginTransaction();
			em = getEntityManager();
			em.persist(assessmentQuestion);
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

	public void edit(AssessmentQuestion assessmentQuestion) throws RollbackFailureException,
			Exception {
		create(assessmentQuestion);
	}

	public void destroy(String id) throws RollbackFailureException, Exception {
		EntityManager em = null;
		try {
			txn = ds.beginTransaction();
			em = getEntityManager();
			AssessmentQuestion assessmentQuestion;
			assessmentQuestion = em.getReference(AssessmentQuestion.class, id);
			em.remove(assessmentQuestion);
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

	public AssessmentQuestion findAssessmentQuestion(String id) {
		EntityManager em = getEntityManager();
		try {
			AssessmentQuestion a = em.find(AssessmentQuestion.class, id);
			a.getAlternatives();
			return a;
		} finally {
			em.close();
		}
	}

}
