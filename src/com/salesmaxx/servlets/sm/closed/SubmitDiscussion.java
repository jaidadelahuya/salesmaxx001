package com.salesmaxx.servlets.sm.closed;

import java.io.IOException;
import java.util.ArrayList;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.KeyFactory;
import com.salesmaxx.beans.CoachingPost;
import com.salesmaxx.beans.SingleDiscussionPageBean;
import com.salesmaxx.entities.Discussion;
import com.salesmaxx.entities.Tag;
import com.salesmaxx.entities.User;
import com.salesmaxx.persistence.controllers.DiscussionController;
import com.salesmaxx.util.Util;

public class SubmitDiscussion extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3614808350072995833L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		Object o = null;
		Discussion d = null;
		User u = null;
		synchronized (session) {
			o = session.getAttribute("coachingPost");
			u = (User) session.getAttribute("user");
		}

		if (o == null) {
			String title = req.getParameter("title");
			String body = req.getParameter("body");
			String tags = req.getParameter("tags");
			String category = req.getParameter("category");
			String privacy = req.getParameter("privacy");
			String notify = req.getParameter("notify-me");
			d = new Discussion();
			CoachingPost cp = new CoachingPost();
			cp.setTitle(title);
			cp.setBody(body);
			cp.setCategory(category);
			cp.setNotify(notify);
			cp.setPrivacy(privacy);
			cp.setTags(tags);
			d = Util.coachingPostToDiscussion(cp, u);
		} else {
			CoachingPost cp = (CoachingPost) o;
			d = Util.coachingPostToDiscussion(cp, u);
			synchronized (session) {
				session.removeAttribute("coachingPost");
			}

		}

		DiscussionController cont = new DiscussionController();
		
		d = cont.create(d, new ArrayList<Tag>());
		if (d.getPrivacy() != null
				&& d.getPrivacy().equalsIgnoreCase("private")) {
			String from = Util.SERVICE_ACCOUNT;
			String to = "stephenu@profiliant.com";
			String title = "Private coaching request from SalesMaxx";
			String webkey = KeyFactory.keyToString(d.getId());
			String body = "<p>Hello,</p>"
					+ "<p>We have a private coaching request. Click <a href='https://salesmaxx001.appspot.com/sm/open/get-discussion?webkey="
					+ webkey + "&privacy=private'>here to view</a></p>";
			req.setAttribute("privateMsgSent", true);
			System.out.println(body);
			RequestDispatcher rd = req
					.getRequestDispatcher("/WEB-INF/sm/open/start-discussion.jsp");
			rd.include(req, resp);
			
			try {
				Util.sendEmail(from, to, title, body);
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			SingleDiscussionPageBean sdpb = Util.discussionToSDPB(d, u);
			synchronized (session) {
				session.setAttribute("singleDiscussionPageBean", sdpb);
			}
			RequestDispatcher rd = req
					.getRequestDispatcher("/WEB-INF/sm/open/view-discussion.jsp");
			rd.include(req, resp);
		}

	}

}
