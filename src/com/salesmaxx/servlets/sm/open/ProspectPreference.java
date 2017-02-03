package com.salesmaxx.servlets.sm.open;

import java.io.Serializable;
import java.util.List;

public abstract class ProspectPreference implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1400758486210218604L;
	protected String solution;
	protected int cursor;
	protected List<PreferenceQuestion> questions;
	@Override
	public String toString() {
		return "ProspectPreference [solution=" + solution + ", cursor="
				+ cursor + ", questions=" + questions + "]";
	}
	public String getSolution() {
		return solution;
	}
	public void setSolution(String solution) {
		this.solution = solution;
	}
	public int getCursor() {
		return cursor;
	}
	public void setCursor(int cursor) {
		this.cursor = cursor;
	}
	public List<PreferenceQuestion> getQuestions() {
		return questions;
	}
	public void setQuestions(List<PreferenceQuestion> questions) {
		this.questions = questions;
	}
	
	public abstract void init();
	
	public abstract String nextUrl();
	
	

}
