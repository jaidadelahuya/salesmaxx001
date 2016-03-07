package com.salesmaxx.servlets.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.entities.WorkshopTemplate;
import com.salesmaxx.persistence.controllers.WorkshopTemplateController;

public class WorkshopScheduleServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3320103489350444805L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		WorkshopTemplateController cont = new WorkshopTemplateController();
		List<WorkshopTemplate> list = cont.getAllWorkshopTemplatesKeys();
		
		HttpSession session = req.getSession();
		synchronized (session) {
			session.setAttribute("workshopTemplates", list);
		}
		
		String url = resp.encodeRedirectURL("/sm-admin/schedule-workshop");
		resp.sendRedirect(url);
	}
}
