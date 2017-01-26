package com.salesmaxx.servlets.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.entities.WorkShop;
import com.salesmaxx.util.Util;

public class ExtendAndAddToWorkshop extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8780958302898192594L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String seatsRequested = req.getParameter("qty");
		String wid = req.getParameter("id");
		String txnRef = req.getParameter("txn-id");
		HttpSession session = req.getSession();

		if (Util.notNull(seatsRequested, wid)) {
			WorkShop w = Util.getWorkshopSchedule(wid);
			long x = Long.parseLong(seatsRequested);
			if (w.getTotalNumberOfSeats() < 30) {
				w.setTotalNumberOfSeats(30);
				long seatsLeft = w.getTotalNumberOfSeats() - w.getNoEnrolled();
				if (x <= seatsLeft) {
					Boolean b = Util.clearManualPayment(txnRef, w, null,
							seatsRequested, false);
					synchronized (session) {
						session.setAttribute("extendSuccess", x
								+ " seats added to ");
					}
				} else {
					synchronized (session) {
						session.setAttribute("extendError", seatsRequested
								+ " seats cannot be added after increasing class size to 30");
					}
				}
			}
		} else {
			synchronized (session) {
				session.setAttribute("extendError",
						"The workshop class size cannot be increased");
			}
		}

		resp.sendRedirect("/sm-admin/reschedule/options?txnRef" + txnRef
				+ "&id=" + wid + "&qty=" + seatsRequested);

	}

}
