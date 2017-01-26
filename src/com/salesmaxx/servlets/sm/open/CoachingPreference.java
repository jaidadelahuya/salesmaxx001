package com.salesmaxx.servlets.sm.open;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class CoachingPreference implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6037287000093352277L;
	private Set<CoachingQuestion> questions;
	public Set<CoachingQuestion> getQuestions() {
		return questions;
	}
	public void setQuestions(Set<CoachingQuestion> questions) {
		this.questions = questions;
	}
	@Override
	public String toString() {
		return "CoachingPreference [questions=" + questions + "]";
	}
	
	

}
