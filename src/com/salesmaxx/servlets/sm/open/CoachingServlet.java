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
import com.salesmaxx.util.Util;

public class CoachingServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3113664812074914593L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//List<Testimonial> list = Util.getTestimonialFromCache("latest-testimonials");
		List<SingleDiscussionPageBean> list2 = Util.getHotestDiscussion();
		//List<FeaturedCoach> list3 = Util.getFeaturedCoaches();
		
		CoachingPageBean cpd = new CoachingPageBean();
		cpd.setDis(list2);
		HttpSession session = req.getSession();
		synchronized (session) {
			session.setAttribute("coachingPageBean", cpd);
		}
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/coaching.jsp");
		rd.include(req, resp);
	}

}
