package com.salesmaxx.servlets.sm.closed;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.beans.ScheduleWorkshopDisplay;
import com.salesmaxx.entities.CanceledWorkshop;
import com.salesmaxx.entities.User;
import com.salesmaxx.entities.WorkShop;
import com.salesmaxx.util.Util;

public class CancelWorkshopServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3524695458809343685L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("id");
		String seats = req.getParameter("seats");
		String mode = req.getParameter("mode");

		if (!Util.notNull(id)) {
			resp.sendRedirect(resp
					.encodeRedirectURL("/sm/closed/profile/my-workshops"));
			return;
		}
		HttpSession session = req.getSession();
		Object o = null;
		synchronized (session) {
			o = session.getAttribute("user");
		}
		if (o != null) {
			User u = (User) o;
			WorkShop w = Util.getWorkshopSchedule(id);
			ScheduleWorkshopDisplay swd = Util.toScheduleWorkshopDisplay(w);
			CanceledWorkshop cw = new CanceledWorkshop(u.getRegId());
			Date d = new Date();
			cw.setCancelDate(d);
			cw.setWorkshopId(id);
			cw.setFormattedCanceledDate(new SimpleDateFormat("dd-MMM-yyyy")
					.format(d));
			cw.setNoOfDelegates(Long.parseLong(seats));
			cw.setWorkshopDate(swd.getStartDate());
			cw.setWorkshopLocation(swd.getLocation().getState());
			cw.setWorkshopName(swd.getName());
			cw.setImageUrl(swd.getImageUrl());
			cw.setEdNoOfDelegates(Long.parseLong(seats));
			if(Util.notNull(mode)) {
				cw.setReschedule(true);
			}
			synchronized (session) {
				session.setAttribute("cancelWorkshop", cw);
				session.setAttribute("oldWorkshop", swd);
			}

			if(Util.notNull(mode)) {
				resp.sendRedirect(resp.encodeRedirectURL("/sm/closed/continue-reschedule"));
				return;
			}else {
				RequestDispatcher rd = req
						.getRequestDispatcher("/WEB-INF/sm/closed/cancel-workshop.jsp");
				rd.include(req, resp);
			}
			

		}

	}

}
