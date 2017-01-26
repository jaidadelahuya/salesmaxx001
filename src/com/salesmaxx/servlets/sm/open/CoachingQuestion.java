package com.salesmaxx.servlets.sm.open;

import java.io.Serializable;

public class CoachingQuestion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2930044037273683350L;
	
	private String question;
	private String answer;
	
	public String asParameter() {
		return question+"="+answer;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	@Override
	public String toString() {
		return "CoachingQuestion [question=" + question + ", answer=" + answer
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((question == null) ? 0 : question.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CoachingQuestion other = (CoachingQuestion) obj;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		return true;
	}
	
	

}
