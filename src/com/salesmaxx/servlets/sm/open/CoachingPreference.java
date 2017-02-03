package com.salesmaxx.servlets.sm.open;

import java.util.ArrayList;
import java.util.List;

public class CoachingPreference extends ProspectPreference {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4316656683502694543L;
	private String[] question = {"Coaching Type","Interview Coaching Type","Sales Performance Coaching Type","Sales Leadership Coaching Type","Nth Interview","Interview Task","Interviewer"};

	@Override
	public void init() {
		int i = 1;
		List<PreferenceQuestion> list = new ArrayList<>();
		for (String s : question) {
			PreferenceQuestion pq = new PreferenceQuestion();
			pq.setQuestion(s);
			pq.setUrl("/sm/open/solutions/coaching/"+i);
			i++;
			list.add(pq);
		}
		setQuestions(list);
		setSolution("coaching");

	}

	@Override
	public String nextUrl() {
		String coachingType = null;
		for(PreferenceQuestion pq : getQuestions()) {
			if(pq.getQuestion().equalsIgnoreCase("Coaching Type")) {
				coachingType = pq.getAnswer();
			}
			if(coachingType.equalsIgnoreCase("interview")) {
				if(pq.getAnswer()==null) {
					return pq.getUrl();
				}
			}else if(coachingType.equalsIgnoreCase("sales performance")) {
				if(pq.getAnswer()==null && pq.getQuestion().equalsIgnoreCase("Sales Performance Coaching Type")) {
					return pq.getUrl();
				}
				
			}else if(coachingType.equalsIgnoreCase("sales management")) {
				if(pq.getAnswer()==null && pq.getQuestion().equalsIgnoreCase("Sales Leadership Coaching Type")) {
					return pq.getUrl();
				}
				
			}
		}
		return null;
	}

}
