package com.salesmaxx.servlets.sm.closed;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.entities.WorkshopTemplate;
import com.salesmaxx.util.Util;

public class ScheduleWorkshop extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7546182449690336846L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("id");
		WorkshopTemplate wt = Util.getWorkshopTemplateFromScheduleId(Util.getWorkshopTemplateFromCache(), id);
		HttpSession session = req.getSession();
		
	}

}
