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
import com.google.appengine.api.datastore.KeyFactory;
import com.salesmaxx.beans.DiscussionPageBean;
import com.salesmaxx.beans.SingleDiscussionPageBean;
import com.salesmaxx.entities.Discussion;
import com.salesmaxx.persistence.controllers.DiscussionController;
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
		String category = req.getParameter("category");
		String privacy = req.getParameter("privacy");
		
		HttpSession session = req.getSession();
		SingleDiscussionPageBean sdpb = null;
		if (privacy != null && privacy.equalsIgnoreCase("private")) {
			Key key = KeyFactory.stringToKey(webKey);
			Discussion d = new DiscussionController().findDiscussion(key);
			if(d != null) {
				List<Discussion> xxx = new ArrayList<>();
				xxx.add(d);
				List<SingleDiscussionPageBean> l = Util.discussionToSDPB(xxx);
				if(l != null && !l.isEmpty()) {
					sdpb = l.get(0);
				}
			}
		} else {
			Object o = null;
			synchronized (session) {
				o = session.getAttribute("discussionPageBean");
			}

			DiscussionPageBean dpb = null;

			if (o != null) {
				dpb = (DiscussionPageBean) o;
				List<SingleDiscussionPageBean> list = dpb.getBeans();
				for (SingleDiscussionPageBean s : list) {
					if (webKey != null && webKey.equals(s.getWebkey())) {
						sdpb = s;
						sdpb.setViews(sdpb.getViews()+1);
						break;
					}
				}
				
				if(sdpb == null) {
					dpb = Util.getDiscussionFromCache(category);
				}

			} else {
				dpb = Util.getDiscussionFromCache(category);
				
			}
			List<SingleDiscussionPageBean> list = dpb.getBeans();
			for (SingleDiscussionPageBean s : list) {
				if (webKey != null && webKey.equals(s.getWebkey())) {
					sdpb = s;
					sdpb.setViews(sdpb.getViews()+1);
					break;
				}
			}
			
		}

		synchronized (session) {
			session.setAttribute("singleDiscussionPageBean", sdpb);
		}
		RequestDispatcher rd = req
				.getRequestDispatcher("/WEB-INF/sm/open/view-discussion.jsp");
		rd.include(req, resp);

	}

}
