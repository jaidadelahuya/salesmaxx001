package com.salesmaxx.servlets.sm.closed;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.beans.RescheduleSummaryBean;
import com.salesmaxx.beans.ScheduleWorkshopDisplay;
import com.salesmaxx.entities.CanceledWorkshop;
import com.salesmaxx.entities.WorkShop;
import com.salesmaxx.util.Util;

public class GetRescheduleSummary extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6970318488429600261L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("id");
		if(Util.notNull(id)) {
			WorkShop w = Util.getWorkshopSchedule(id);
			ScheduleWorkshopDisplay swd = Util.toScheduleWorkshopDisplay(w);
			HttpSession session = req.getSession();
			Object o = null;
			Object o1 = null;
			synchronized (session) {
				o = session.getAttribute("cancelWorkshop");
				o1 = session.getAttribute("oldWorkshop");
			}
			if(o!=null && o1 != null) {
				ScheduleWorkshopDisplay oldW = (ScheduleWorkshopDisplay) o1;
				CanceledWorkshop cw = (CanceledWorkshop) o;
				RescheduleSummaryBean rsb = new RescheduleSummaryBean();
				rsb.setCostPerDelegates(5000);
				rsb.setNewWorkshop(swd);
				rsb.setNoDelegates(cw.getNoOfDelegates());
				rsb.setOldWorkshop(oldW);
				synchronized (session) {
					session.setAttribute("rescheduleSummaryBean", rsb);
				}
				RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/sm/closed/reschedule-summary.jsp");
				rd.forward(req, resp);
			} 
		}
		
		
	}

}
