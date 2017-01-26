package com.salesmaxx.servlets.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.EmbeddedEntity;
import com.google.appengine.api.datastore.Key;
import com.salesmaxx.beans.ChequeInvoice.InvoiceStatus;
import com.salesmaxx.entities.ManualTransaction;
import com.salesmaxx.entities.WorkShop;
import com.salesmaxx.persistence.controllers.ManualTransactionController;
import com.salesmaxx.persistence.controllers.WorkshopController;
import com.salesmaxx.util.Util;

public class AddToWorkshop extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8369310915295429931L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("id");

		HttpSession session = req.getSession();

		Object o = null;

		synchronized (session) {
			o = session.getAttribute("rescheduleOptions");
		}

		if (o != null) {
			RescheduleOption ro = (RescheduleOption) o;
			String txnRef = ro.getTxnRef();
			
			WorkShop w = Util.getWorkshopSchedule(id);
			WorkShop ow = Util.getWorkshopSchedule(ro.getOid());
			Boolean b = Util.clearManualPayment(txnRef, w, ow, ro.getQty(),
					true);

			resp.sendRedirect(resp
					.encodeRedirectURL("/sm-admin/transaction/manual/reschedule/complete"));

		}
	}

}
