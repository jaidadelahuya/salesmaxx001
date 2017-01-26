package com.salesmaxx.servlets.sm.open;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.util.Util;

public class CoachingPreferenceServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1130420598899728946L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String question = req.getParameter("question");
		String answer = req.getParameter("answer");
		String redirect = req.getParameter("redirect");
		if (Util.notNull(question, answer, redirect)) {
			HttpSession session = req.getSession();
			Object o = null;
			synchronized (session) {
				o = session.getAttribute("coachingPrefence");
			}
			CoachingPreference cp = null;
			if (o == null) {
				cp = new CoachingPreference();
			} else {
				cp = (CoachingPreference) o;
			}

			CoachingQuestion cq = new CoachingQuestion();
			if (answer.equalsIgnoreCase("other")) {
				cq.setAnswer(req.getParameter("other-answer"));
			} else {
				cq.setAnswer(answer);
			}
			cq.setQuestion(question);

			Set<CoachingQuestion> l = cp.getQuestions();
			if (l == null) {
				l = new HashSet<>();
			}
			l.add(cq);
			cp.setQuestions(l);
			synchronized (session) {
				session.setAttribute("coachingPrefence", cp);
			}
			if (redirect.equalsIgnoreCase("pq2")
					| redirect.equalsIgnoreCase("oq2")) {
				redirect = "/coaching/organization-question-1";//
			} else if (redirect.equalsIgnoreCase("pq3")) {
				redirect = "/coaching/personal-question-2";
			} else if (redirect.equalsIgnoreCase("fq")) {
				redirect = "/coaching/preference/result";
			} else if (redirect.equalsIgnoreCase("wq")) {
				Set<CoachingQuestion> set = cp.getQuestions();
				Iterator<CoachingQuestion> it = set.iterator();
				String query = "";
				for (int i = 0; i < set.size(); i++) {
					CoachingQuestion c = it.next();
					query += c.asParameter();

					if (i < set.size() - 1) {
						query += "&";
					}
				}
				redirect = "/sm/open/search-for-workshop" + "?" + query;
				synchronized (session) {
					session.removeAttribute("coachingPreference");
				}
			}
			resp.sendRedirect(redirect);
		} else {
			resp.sendRedirect("/");
		}

	}

}
