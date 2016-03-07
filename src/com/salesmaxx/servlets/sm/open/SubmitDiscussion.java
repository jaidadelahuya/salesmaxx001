package com.salesmaxx.servlets.sm.open;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;
import com.salesmaxx.entities.Discussion;
import com.salesmaxx.entities.User;
import com.salesmaxx.util.Util;

public class SubmitDiscussion extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2918695552299870330L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String title = req.getParameter("title");
		String body = req.getParameter("body");
		String tags = req.getParameter("tags");
		String privacy = req.getParameter("privacy");
		String category = req.getParameter("category");
		String notify = req.getParameter("notify-me");
		
		HttpSession session = req.getSession();
		
		boolean ok = Util.notNull(title);
		if(!ok) {
			synchronized (session) {
				session.setAttribute("", true);
				session.setAttribute("", "You have to enter a title for your question/discussion");
			}
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/sm/open/start-discussion.jsp");
			rd.include(req, resp);
		}
		ok = Util.notNull(body);
		if(!ok) {
			synchronized (session) {
				session.setAttribute("", true);
				session.setAttribute("", "You have to enter a body for your question/discussion");
			}
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/sm/open/start-discussion.jsp");
			rd.include(req, resp);
		}
		
		User user = null;
		
		
		Discussion  d = new Discussion();
		d.setBody(new Text(body));
		d.setCategory(category);
		//List<Key> comms = new ArrayList<>();
		d.setComments(new ArrayList<Key>());
		List<String> emails = new ArrayList<>();
		if(notify.equalsIgnoreCase("true")) {
			emails.add(notify);
		}
		d.setEmailsToNotify(emails);
		
		if(privacy.equalsIgnoreCase("public")) {
			
			
		} else if(privacy.equalsIgnoreCase("private")) {
			
		}
		
	}
}
