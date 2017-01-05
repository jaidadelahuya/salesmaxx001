package com.salesmaxx.servlets.sm.closed;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.beans.ChequeInvoice;
import com.salesmaxx.entities.ManualTransaction;
import com.salesmaxx.persistence.controllers.ManualTransactionController;
import com.salesmaxx.util.Util;

public class GetChequeInvoice extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1612324215636433072L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String txnRef = req.getParameter("txnRef");
		HttpSession session = req.getSession();
		if (Util.notNull(txnRef)) {
			synchronized (session) {
				Object o = session.getAttribute("chequeTxnRef");
				if (o != null) {
					txnRef = (String) o;

				}
			}
			if (txnRef != null) {
				ManualTransactionController c = new ManualTransactionController();
				List<ManualTransaction> mts = c.findByTxnRef(txnRef,
						ChequeInvoice.InvoiceStatus.PENDING);
				if (mts.size() == 1) {
					ChequeInvoice cq = ClosedUtil
							.manualTransactionToChequeInvoice(mts.get(0));
					synchronized (session) {
						session.setAttribute("chequeInvoice", cq);
					}
					resp.sendRedirect("/sm/open/invoice/view");
				}

			}

		}
	}

}
