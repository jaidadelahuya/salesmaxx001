package com.salesmaxx.servlets.sm.open;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.salesmaxx.beans.SingleDiscussionPageBean;
import com.salesmaxx.entities.Discussion;
import com.salesmaxx.entities.User;
import com.salesmaxx.persistence.controllers.DiscussionController;
import com.salesmaxx.persistence.controllers.UserController;
import com.salesmaxx.util.Util;

public class GetDiscussion extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4968855361616234780L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String webKey = req.getParameter("webkey");
		HttpSession session = req.getSession();
		SingleDiscussionPageBean sdpb = null;
		Key key = KeyFactory.stringToKey(webKey);
		DiscussionController c = new DiscussionController();
		Discussion d = c.findDiscussion(key);
		User u = new UserController().findUser(d.getOwner());
		sdpb = Util.discussionToSDPB(d, u);
			
		
		synchronized (session) {
			session.setAttribute("singleDiscussionPageBean", sdpb);
		}
		RequestDispatcher rd = req
				.getRequestDispatcher("/WEB-INF/sm/open/view-discussion.jsp");
		rd.include(req, resp);

	}

}
