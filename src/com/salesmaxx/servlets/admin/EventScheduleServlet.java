package com.salesmaxx.servlets.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.entities.EventTemplate;
import com.salesmaxx.persistence.controllers.EventTemplateController;

public class EventScheduleServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5765373937229828327L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		EventTemplateController cont = new EventTemplateController();
		List<EventTemplate> list = cont.getAllEventTemplatesKeys();
		HttpSession session = req.getSession();
		synchronized (session) {
			session.setAttribute("eventTemplates", list);
		}
		
		String url = resp.encodeRedirectURL("/sm-admin/schedule-event");
		resp.sendRedirect(url);
	}
}
