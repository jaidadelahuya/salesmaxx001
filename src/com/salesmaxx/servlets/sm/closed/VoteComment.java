package com.salesmaxx.servlets.sm.closed;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Transaction;
import com.salesmaxx.beans.CommentBean;
import com.salesmaxx.beans.SingleDiscussionPageBean;
import com.salesmaxx.entities.Comment;
import com.salesmaxx.entities.User;
import com.salesmaxx.entities.Vote;
import com.salesmaxx.persistence.controllers.CommentController;
import com.salesmaxx.persistence.controllers.EMF;
import com.salesmaxx.util.Util;

public class VoteComment extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7015095727817198817L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("id");
		String type = req.getParameter("type");

		HttpSession session = req.getSession();
		Object o = null;
		Object o1 = null;
		synchronized (session) {
			o = session.getAttribute("user");
			o1 = session.getAttribute("singleDiscussionPageBean");
		}

		if (o != null && o1 != null & Util.notNull(id, type)) {
			User u = (User) o;
			SingleDiscussionPageBean sdpb = (SingleDiscussionPageBean) o1;
			List<CommentBean> list = sdpb.getComments();
			for (CommentBean cb : list) {
				if (cb.getWebkey().equals(id)) {
					boolean hasVoted = Util.checkVotingStatus(u, cb);
					resp.setContentType("application/json");
					if (hasVoted) {
						resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, "You have already voted for this comment");
						return;
					} else {
						
						if(type.equalsIgnoreCase("up")) {
							cb.setUpVotes(cb.getUpVotes()+1);
							resp.getWriter().write(String.valueOf(cb.getUpVotes()));
						}else if(type.equalsIgnoreCase("down")) {
							cb.setDownVotes(cb.getDownVotes()+1);
							resp.getWriter().write(String.valueOf(cb.getDownVotes()));
						}
						synchronized (session) {
							session.setAttribute("singleDiscussionPageBean", sdpb);
						}
						Vote v = new Vote();
						v.setType(type);
						v.setVoter(u.getRegId());
						v.setCommentKey(KeyFactory.stringToKey(id));
						Transaction txn = EMF.getDs().beginTransaction();
						EMF.getDs().put(v.toEntity());
						txn.commitAsync();
						
					}

				}
			}

		}
	}
}
