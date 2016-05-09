package com.salesmaxx.servlets.sm.closed;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.beans.ScheduleWorkshopDisplay;
import com.salesmaxx.entities.CanceledWorkshop;
import com.salesmaxx.entities.WorkShop;
import com.salesmaxx.entities.WorkshopTemplate;
import com.salesmaxx.util.Util;

public class ContinueReschedule extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3049974702698125567L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String id = req.getParameter("id");
		HttpSession session = req.getSession();
		Object o = null;
		List<WorkShop> schedules = null;
		List<ScheduleWorkshopDisplay> swd = null;
		WorkshopTemplate wt = null;
		synchronized (session) {
			o = session.getAttribute("cancelWorkshop");
		}
		if (Util.notNull(id)) {
			wt = Util.getWorkshopFromList(id,
					Util.getWorkshopTemplateFromCache());
		} else if (o != null) {
			CanceledWorkshop cw = (CanceledWorkshop) o;
			wt = Util.getWorkshopTemplateFromScheduleId(
					Util.getWorkshopTemplateFromCache(), cw.getWorkshopId());

		} else {
			resp.getWriter().write(
					"<a href='/login'>You have to login again</a>");
			return;
		}
		schedules = Util.getScheduledWorkshops(wt.getSchedules());
		swd = Util.toScheduleWorkshopDisplay(schedules);
		synchronized (session) {
			session.setAttribute("workshopTemplate", wt);
			session.setAttribute("workshopSchedules", swd);
		}
		resp.sendRedirect(resp.encodeRedirectURL("/sm/close/reschedule"));
	}

}
