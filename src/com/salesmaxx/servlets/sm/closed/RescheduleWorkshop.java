package com.salesmaxx.servlets.sm.closed;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.salesmaxx.beans.ScheduleWorkshopDisplay;
import com.salesmaxx.entities.WorkShop;
import com.salesmaxx.entities.WorkshopTemplate;
import com.salesmaxx.util.Util;

public class RescheduleWorkshop extends HttpServlet {

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
		List<WorkshopTemplate> temps = new ArrayList<>();
		temps.add(wt);
		List<WorkShop> list = Util.getAllScheduleWorkshopFromTemplate(temps);
		List<ScheduleWorkshopDisplay> swd = Util.toScheduleWorkshopDisplay(list);
		Key key = KeyFactory.createKey(wt.getWorkshopId(), WorkShop.class.getSimpleName(),Long.parseLong(id));
		synchronized (session) {
			session.setAttribute("availableSchedules", swd);
			session.setAttribute("workshopToBeRescheduled",key);
		}
		
		RequestDispatcher rd =  req.getRequestDispatcher("/WEB-INF/sm/closed/reschedule-workshop.jsp");
		rd.include(req, resp);
		
	}

}
