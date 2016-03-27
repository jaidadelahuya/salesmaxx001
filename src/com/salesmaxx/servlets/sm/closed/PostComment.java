package com.salesmaxx.servlets.sm.closed;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.KeyRange;
import com.google.appengine.api.datastore.Text;
import com.salesmaxx.beans.CommentBean;
import com.salesmaxx.beans.SingleDiscussionPageBean;
import com.salesmaxx.entities.Comment;
import com.salesmaxx.entities.Discussion;
import com.salesmaxx.entities.User;
import com.salesmaxx.persistence.controllers.DiscussionController;
import com.salesmaxx.persistence.controllers.EMF;
import com.salesmaxx.util.Util;

public class PostComment extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1099995782642283517L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();

		Object o = null;
		Object o1 = null;
		Object o2 = null;
		Object o3 = null;

		synchronized (session) {
			o = session.getAttribute("newComment");
			o1 = session.getAttribute("newPostWebkey");
			o2 = session.getAttribute("singleDiscussionPageBean");
			o3 = session.getAttribute("user");
		}
		Comment comment = null;
		User u = null;
		String webkey = null;
		if (o != null && o1 != null && o2 != null && o3 != null) {
			webkey = (String) o1;
			Key k = KeyFactory.stringToKey(webkey);
			u = (User) o3;
			comment = new Comment();
			comment.setBody(new Text((String) o));
			comment.setComments(new ArrayList<Key>());
			comment.setLikers(new ArrayList<Key>());
			comment.setTime(new Date());
			comment.setOwner(u.getRegId());
			KeyRange range = EMF.getDs().allocateIds(
					Comment.class.getSimpleName(), 1);
			comment.setId(range.getStart());
			CommentBean cb = Util.createCommentBean(comment, u, true);
			SingleDiscussionPageBean sdpb = (SingleDiscussionPageBean) o2;
			List<CommentBean> l = sdpb.getComments();
			if (l == null) {
				l = new ArrayList<CommentBean>();
			}
			l.add(0, cb);
			sdpb.setComments(l);
			synchronized (session) {
				session.setAttribute("singleDiscussionPageBean", sdpb);
				session.removeAttribute("newComment");
				session.removeAttribute("newPostWebkey");
			}

			// Util.updateDiscussionInCache(sdpb);
			DiscussionController c = new DiscussionController();
			Discussion d = c.findDiscussion(k);
			String body = "<p>"
					+ u.getFirstName()
					+ " made a comment on the post: <a href='https://salesmaxx001.appspot.com/sm/open/get-discussion?webkey="
					+ webkey + "&privacy=private'>" + d.getTitle() + "</a></p>";
			String title = "New comment on "+d.getCategory();
			Util.sendEmailNotification(d.getEmailsToNotify(), title, body);
			List<Key> lc = d.getComments();
			if (lc == null) {
				lc = new ArrayList<Key>();
			}
			lc.add(0, comment.getId());
			if(d.getEmailsToNotify() == null) {
				d.setEmailsToNotify(new ArrayList<String>());
			}
			if(!d.getEmailsToNotify().contains(u.getUsername())) {
				d.getEmailsToNotify().add(u.getUsername());
			}
			d.setComments(lc);
			c.edit(d, comment);

			String url = resp
					.encodeRedirectURL("/sm/open/get-discussion?webkey="
							+ sdpb.getWebkey() + "&category="
							+ sdpb.getCategory());
			resp.sendRedirect(url);
		}
	}

}
