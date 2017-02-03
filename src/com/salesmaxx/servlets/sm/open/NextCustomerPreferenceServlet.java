package com.salesmaxx.servlets.sm.open;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class NextCustomerPreferenceServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4642971889946590611L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String answer = req.getParameter("answer");
		
		Object o = null;
		HttpSession session = req.getSession();
		synchronized (session) {
			o = session.getAttribute("prospectPreference");
		}
		
		if( o != null) {
			
			ProspectPreference pp = (ProspectPreference) o;
			int i = pp.getCursor();
			PreferenceQuestion pq = pp.getQuestions().get(i);
			pq.setAnswer(answer);
			pp.getQuestions().remove(i);
			pp.getQuestions().add(i, pq);
			i = i+1;
			pp.setCursor(i);
			String url = pp.nextUrl();
			synchronized (session) {
				session.setAttribute("prospectPreference", pp);
			}
			resp.sendRedirect(url);
			
		}
	}

}
