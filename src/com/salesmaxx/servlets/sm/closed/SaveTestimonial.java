package com.salesmaxx.servlets.sm.closed;

import java.io.IOException;
import java.security.KeyFactory;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyRange;
import com.google.appengine.api.datastore.Text;
import com.salesmaxx.entities.Testimonial;
import com.salesmaxx.entities.User;
import com.salesmaxx.persistence.controllers.EMF;
import com.salesmaxx.persistence.controllers.TestimonialController;

public class SaveTestimonial extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9093793210063076160L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		User u = null;
		String statement = null;
		synchronized (session) {
			u = (User) session.getAttribute("user");
			statement =  (String) session.getAttribute("testimonialStatement");
		}
		Testimonial t = new Testimonial();
		t.setDate(new Date());
		t.setStatement(new Text(statement));
		t.setWriter(u.getRegId());
	
		KeyRange range = EMF.getDs().allocateIds(Testimonial.class.getSimpleName(),1);
		Key k = range.getStart();
		t.setId(k);
		
		String webkey = com.google.appengine.api.datastore.KeyFactory.keyToString(k);
		
		TestimonialController c = new TestimonialController();
		c.create(t);
		
		synchronized (session) {
			session.setAttribute("newTestimonialSaved", true);
		}
		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/sm/open/show-testimonials");
		rd.forward(req, resp);
	}

}
