package com.salesmaxx.servlets.sm.open;

import java.io.Serializable;

public class PreferenceQuestion implements Serializable {
	private String question;
	private String answer;
	private String url;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "PreferenceQuestion [question=" + question + ", answer="
				+ answer + ", url=" + url + "]";
	}
	
}
