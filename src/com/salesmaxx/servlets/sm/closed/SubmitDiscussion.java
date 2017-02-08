package com.salesmaxx.servlets.sm.closed;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

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
			String anonymous = req.getParameter("anonymous");
			String category = req.getParameter("category");
			String privacy = req.getParameter("privacy");
			String notify = req.getParameter("notify-me");
			d = new Discussion();
			CoachingPost cp = new CoachingPost();
			cp.setTitle(title);
			cp.setBody(body);
			cp.setCategory(category);
			if (Util.notNull(privacy)) {
				cp.setPrivacy(true);
			}

			if (Util.notNull(anonymous)) {
				cp.setAnonymous(true);
			}

			if (Util.notNull(notify)) {
				cp.setNotify(true);
			}

			d = Util.coachingPostToDiscussion(cp, u);
		} else {
			CoachingPost cp = (CoachingPost) o;
			d = Util.coachingPostToDiscussion(cp, u);
			synchronized (session) {
				session.removeAttribute("coachingPost");
			}

		}
		
		Object o1 = null;
		Map<String, String> map = null;
		synchronized (session) {
			o1 = session.getAttribute("qaMap");
		}
		
		if(o1!=null) {
			map = (Map<String, String>) o1;
			d = Util.tagDiscussion(map, d);
		}

		DiscussionController cont = new DiscussionController();

		d = cont.create(d);
		if (d.getPrivacy() != null
				&& d.getPrivacy().equalsIgnoreCase("private")) {
			String toUser = u.getUsername();
			if (toUser == null && u.getEmails() != null) {
				for (String s : u.getEmails()) {
					toUser = s;
					break;
				}
			}
			String from = Util.SERVICE_ACCOUNT;
			String to = "stephenu@profiliant.com";
			String title = "Private coaching request from SalesMaxx";
			String webkey = KeyFactory.keyToString(d.getId());
			String body = "<p>Hello Admin,</p>"
					+ "<p>"
					+ u.getFirstName()
					+ " "
					+ u.getLastName()
					+ " has requested a private coaching request.</p>"
					+ "<p><strong>Request Details</strong><br/><p><strong>Email: </strong>"
					+ toUser
					+ "</p><p>"
					+ d.getBody().getValue()
					+ "</p></p>"
					+ "<p>Click <a href='http://www.salesmaxx.com/sm/open/get-discussion?webkey="
					+ webkey + "&privacy=private'>here to view</a></p>";
			req.setAttribute("privateMsgSent", true);
			System.out.println(body);

			String userTitle = "Your private coaching request has been recieved";
			String userBody = "<p>Hello "
					+ u.getFirstName()
					+ "</p><p>Your coaching request has been recieved. One of our coaches will contact you shortly.</p><p>Warm Regards<br>SalesMaxx Admin</p>";

			resp.sendRedirect("/sm/close/coaching/request-success");
			try {
				Util.sendEmail(from, to, title, body);
				Util.sendEmail(from, toUser, userTitle, userBody);
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
			resp.sendRedirect("/coaching/discussion?web-key="
					+ sdpb.getWebkey());
		}

	}
}
