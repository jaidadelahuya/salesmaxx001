package com.salesmaxx.servlets.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.beans.ScheduleWorkshopDisplay;
import com.salesmaxx.entities.WorkShop;
import com.salesmaxx.entities.WorkshopTemplate;
import com.salesmaxx.util.Util;

public class GetWorkshopSchedule extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 962266760657594793L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String txnRef = req.getParameter("txn-ref");
		String id = req.getParameter("id");
		String qty = req.getParameter("qty");
		String workshopName = req.getParameter("workshop-name");

		if (Util.notNull(workshopName, txnRef,id,qty)) {
			String wid = Util.getWorkshopId(workshopName);
			WorkshopTemplate wt = Util.getWorkshopFromList(wid,
					Util.getWorkshopTemplateFromCache());
			List<WorkShop> schedules = Util.getScheduledWorkshops(wt
					.getSchedules());
			List<ScheduleWorkshopDisplay> sch = Util
					.toScheduleWorkshopDisplay(schedules);
			RescheduleOption ro = new RescheduleOption();
			ro.setOid(id);
			ro.setQty(qty);
			ro.setSchedules(sch);
			ro.setTxnRef(txnRef);
			ro.setWorkshop(workshopName);
			HttpSession session = req.getSession();
			synchronized (session) {
				session.setAttribute("rescheduleOptions",ro);
			}
			
			resp.sendRedirect(resp.encodeRedirectURL("/sm-admin/2/reschedule/options"));
			
		}
	}

}
