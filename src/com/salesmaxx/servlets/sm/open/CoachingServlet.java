package com.salesmaxx.servlets.sm.open;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.beans.CoachingPageBean;
import com.salesmaxx.beans.SingleDiscussionPageBean;
import com.salesmaxx.entities.Facilitator;
import com.salesmaxx.persistence.controllers.FacilitatorController;
import com.salesmaxx.util.Util;

public class CoachingServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3113664812074914593L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		
		CoachingPageBean cpd = null;
		
		HttpSession session = req.getSession();
		Object o = null;
		synchronized (session) {
			o = session.getAttribute("coachingPageBean");
			if(o!=null) {
				cpd = (CoachingPageBean) o;
			}
			
		}
		if(cpd==null) {
			List<SingleDiscussionPageBean> list = Util.getHotestDiscussion();
			List<SingleDiscussionPageBean> l = Util.getNewestDiscussion();
			
			cpd = new CoachingPageBean();
			cpd.setDis(l);
			cpd.setTrending(list);
			
			
		}
		
		synchronized (session) {
			session.setAttribute("coachingPageBean", cpd);
		}
		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/coaching.jsp");
		rd.include(req, resp);
	}

}
