package com.salesmaxx.servlets.sm.open;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.util.Util;

public class InitProspectPreference extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8494854811467984280L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String solution = req.getParameter("solution");
		
		if(Util.notNull(solution)) {
			ProspectPreference pp = ProspectPreferenceFactory.createProspectPeference(solution);
			HttpSession session = req.getSession();
			synchronized (session) {
				session.setAttribute("prospectPreference", pp);
			}
			resp.sendRedirect(pp.getQuestions().get(0).getUrl());
		}else {
			resp.sendRedirect("/sm/open/solutions");
		}
	}

}
