package com.salesmaxx.servlets.sm.open;

import java.util.ArrayList;
import java.util.List;



public class WorkshopPreference extends ProspectPreference {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6573800292142613988L;
	private String[] question = { "experience",
			"industry",
			"job-role" };

	@Override
	public void init() {
		int i = 1;
		List<PreferenceQuestion> list = new ArrayList<>();
		for (String s : question) {
			PreferenceQuestion pq = new PreferenceQuestion();
			pq.setQuestion(s);
			pq.setUrl("/sm/open/solutions/workshop/"+i);
			i++;
			list.add(pq);
		}
		setQuestions(list);
		setSolution("workshop");

	}

	@Override
	public String nextUrl() {
		return questions.get(cursor).getUrl();
	}

}
