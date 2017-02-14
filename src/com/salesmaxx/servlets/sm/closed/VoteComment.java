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
import com.salesmaxx.beans.CommentBean;
import com.salesmaxx.beans.SingleDiscussionPageBean;
import com.salesmaxx.entities.Comment;
import com.salesmaxx.entities.User;
import com.salesmaxx.entities.Vote;
import com.salesmaxx.persistence.controllers.CommentController;
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
		
		if(o!=null && o1!=null& Util.notNull(id,type)) {
			User u = (User) o;
			SingleDiscussionPageBean sdpb = (SingleDiscussionPageBean) o1;
			List<CommentBean> list = sdpb.getComments();
			for(CommentBean cb:list) {
				if(cb.getWebkey().equals(id)) {
					CommentController c = new CommentController();
					Comment comm = c.findComment(KeyFactory.stringToKey(id));
					
					List<String> l = null;
					if(id.equalsIgnoreCase("up")) {
						l = (cb.getUpVoters()==null)?cb.getUpVoters():new ArrayList<String>();
						if(l.contains(KeyFactory.keyToString(u.getRegId())) && !comm.getUpvote().contains(u.getRegId())) {
							//send message
						}else {
							l.add(KeyFactory.keyToString(u.getRegId()));
							comm.getUpvote().add(u.getRegId());
						}
						
					}else if(id.equalsIgnoreCase("down")) {
						l = (cb.getDownVoters()==null)?cb.getUpVoters():new ArrayList<String>();
						if(l.contains(KeyFactory.keyToString(u.getRegId())) && !comm.getDownVote().contains(u.getRegId())) {
							//send message
						}else {
							l.add(KeyFactory.keyToString(u.getRegId()));
							comm.getDownVote().add(u.getRegId());
						}
					}
					
					Vote v = new Vote();
					v.setType(type);
					v.setVoter(u.getRegId());
					
				}
			}
			
		}
	}

}
