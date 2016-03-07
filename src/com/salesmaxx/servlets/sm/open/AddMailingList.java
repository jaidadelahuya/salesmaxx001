package com.salesmaxx.servlets.sm.open;

import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;
import com.salesmaxx.entities.MailingList;
import com.salesmaxx.persistence.controllers.MailingListController;
import com.salesmaxx.util.Util;

public class AddMailingList extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9070204651272090117L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String email = req.getParameter("email");

		boolean ok = Util.notNull(email);

		if (ok) {
			/*MailingList ml = new MailingList();
			ml.setEmail(email);
			MailingListController c = new MailingListController();
			c.create(ml);*/
			
			Queue q = QueueFactory.getDefaultQueue();
			q.add(TaskOptions.Builder.withUrl("/sm/open/newsletter-subscription").param("email", email));

			
		}

	}
}
