package com.salesmaxx.servlets.sm.open;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.entities.Discussion;
import com.salesmaxx.persistence.controllers.DiscussionController;

public class StartDiscussion extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3638402951348567528L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String category = req.getParameter("category");

		List<Discussion> list = new DiscussionController()
				.findDiscussionsByCategory(category);
		
		HttpSession session = req.getSession();
		synchronized (session) {
			session.setAttribute("coachingCategory", category);
			session.setAttribute("coachingCategoryDiscussions", list);
		}
		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/sm/open/start-discussion.jsp");
		rd.include(req, resp);
	}

}
