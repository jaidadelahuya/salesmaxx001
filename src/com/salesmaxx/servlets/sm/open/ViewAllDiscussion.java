package com.salesmaxx.servlets.sm.open;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.beans.DiscussionPageBean;
import com.salesmaxx.beans.SingleDiscussionPageBean;
import com.salesmaxx.entities.Discussion;
import com.salesmaxx.entities.User;
import com.salesmaxx.util.Util;

public class ViewAllDiscussion extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8737295884813384727L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
			String category = req.getParameter("category");
			DiscussionPageBean dpb = Util.getDiscussionFromCache(category);
			HttpSession session = req.getSession();
			synchronized (session) {
				session.setAttribute("discussionPageBean", dpb);
			}
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/sm/open/view-all-discussions.jsp");
			rd.include(req, resp);
		
	}

}
