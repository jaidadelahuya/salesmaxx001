package com.salesmaxx.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.datanucleus.api.jpa.annotations.Extension;

import com.google.appengine.api.datastore.Text;

@Entity
public class AssessmentQuestion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7434957293281864028L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Basic
	
	private Text question;
	
	@Basic
	
	private List<String> alternatives;
	
	@Basic
	
	private AlternativeType altType;
	
	
	private String correctAnswer;
	
	@Basic
	
	private Text explanation;
	
	@Transient
	
	private String userChoice;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Text getQuestion() {
		return question;
	}

	public void setQuestion(Text question) {
		this.question = question;
	}

	public List<String> getAlternatives() {
		return alternatives;
	}

	public void setAlternatives(List<String> alternatives) {
		this.alternatives = alternatives;
	}

	public AlternativeType getAltType() {
		return altType;
	}

	public void setAltType(AlternativeType altType) {
		this.altType = altType;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public Text getExplanation() {
		return explanation;
	}

	public void setExplanation(Text explanation) {
		this.explanation = explanation;
	}

	public String getUserChoice() {
		return userChoice;
	}

	public void setUserChoice(String userChoice) {
		this.userChoice = userChoice;
	}

	@Override
	public String toString() {
		return "AssessmentQuestion [id=" + id + ", question=" + question
				+ ", alternatives=" + alternatives + ", altType=" + altType
				+ ", correctAnswer=" + correctAnswer + ", explanation="
				+ explanation + ", userChoice=" + userChoice + "]";
	}
	
	
	
	
	
}
