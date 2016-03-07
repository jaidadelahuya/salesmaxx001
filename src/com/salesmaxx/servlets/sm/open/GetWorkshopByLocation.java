package com.salesmaxx.servlets.sm.open;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.beans.ScheduleWorkshopDisplay;
import com.salesmaxx.entities.WorkShop;
import com.salesmaxx.util.Util;

public class GetWorkshopByLocation extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -532864701695761977L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String location = req.getParameter("location");
		List<WorkShop> workshops = Util.getAllWorkshopSchedulesFromCache();
		List<WorkShop> temps = Util.findWorkshopByLocation(location,workshops);
		List<ScheduleWorkshopDisplay> swds = Util.toScheduleWorkshopDisplay(temps);
		HttpSession session = req.getSession();
		Util.initWorkshopLayout(session, resp);

		synchronized (session) {
			session.setAttribute("workshopSchedules", swds);
			session.setAttribute("searchFor", location.toUpperCase());
		}

		RequestDispatcher rd = req
				.getRequestDispatcher("/WEB-INF/sm/open/find-workshop-by-prop.jsp");
		rd.include(req, resp);
	}

}
