package com.salesmaxx.servlets.sm.open;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.QueryResultList;
import com.salesmaxx.beans.DiscussionPageBean;
import com.salesmaxx.beans.SingleDiscussionPageBean;
import com.salesmaxx.entities.Discussion;
import com.salesmaxx.persistence.controllers.DiscussionController;
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
			HttpSession session = req.getSession();
			List<Key> keys = new ArrayList<>();
			if(Util.notNull(category)) {
				QueryResultList<Entity> ents = new DiscussionController().getDiscussions(category, 10, true);
				DiscussionPageBean dpb = new DiscussionPageBean();
				dpb.setCursor((ents.getCursor()==null)?null:ents.getCursor().toWebSafeString());
				for (Entity e : ents) {
					keys.add(e.getKey());
				}
				List<Discussion> discussions = Util.getDiscussionFromCache(keys);
				List<SingleDiscussionPageBean> l=  Util.discussionToSDPB(discussions);
				Collections.sort(l, new Comparator<SingleDiscussionPageBean>() {

					@Override
					public int compare(SingleDiscussionPageBean o1,
							SingleDiscussionPageBean o2) {
						return o2.getTime().compareTo(o1.getTime());
						
					}

					
				});
				dpb.setBeans(l);
				dpb.setCategory(category);
				Map<String,List<SingleDiscussionPageBean>> map = Util.getDiscussions(category);
				dpb.setOtherCategory(map);
				synchronized (session) {
					session.setAttribute("discussionPageBean", dpb);
				}
				
				resp.sendRedirect("/coaching/view");
				
				
			}
			
			
		
			
			
		
	}

}
