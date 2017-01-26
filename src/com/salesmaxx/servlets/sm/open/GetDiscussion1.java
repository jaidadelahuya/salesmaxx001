package com.salesmaxx.servlets.sm.open;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.salesmaxx.beans.SingleDiscussionPageBean;
import com.salesmaxx.entities.Discussion;
import com.salesmaxx.entities.User;
import com.salesmaxx.persistence.controllers.EMF;
import com.salesmaxx.util.Util;

public class GetDiscussion1 extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8208970160506311274L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String webKey = req.getParameter("web-key");
		
		if(Util.notNull(webKey)) {
			Key key = KeyFactory.stringToKey(webKey);
			try {
				Entity e = EMF.getDs().get(key);
				Discussion d = Util.entityToDiscussion(e);
				Key owner = d.getOwner();
				Entity e1 = EMF.getDs().get(owner);
				User u = Util.toUser(e1);
				SingleDiscussionPageBean sd = Util.discussionToSDPB(d, u);
				HttpSession session = req.getSession();
				synchronized (session) {
					session.setAttribute("singleDiscussionPageBean", sd);
				}
				req.getRequestDispatcher("/WEB-INF/sm/open/view-discussion.jsp").include(req, resp);
			} catch (EntityNotFoundException e) {
				// TODO Auto-generated catch block
				resp.sendRedirect("/");
			}
		}
	}

}
