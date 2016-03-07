package com.salesmaxx.servlets.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ScheduleWorkshop extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -786300834884350330L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String workshopName = req.getParameter("workshop-name");
		String venue = req.getParameter("venue");
		String Street = req.getParameter("street");
		String city = req.getParameter("city");
		String state = req.getParameter("state");
		String country = req.getParameter("country");
		String startDate = req.getParameter("start-date");
		String endDate = req.getParameter("end-date");
		String format = req.getParameter("format");
		String type = req.getParameter("type");
		String[] facilitators = req.getParameterValues("facilitators");
	}

}
