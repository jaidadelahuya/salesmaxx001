package com.salesmaxx.servlets.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.beans.ChequePaymentBean;
import com.salesmaxx.util.Util;

public class GetChequePayment extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1236646630387007568L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String txnID = req.getParameter("txn-id");
		String category = req.getParameter("category");
		String uid = req.getParameter("uid");

		if (Util.notNull(txnID, category) | Util.notNull(category, uid)) {
			HttpSession session = req.getSession();
			ChequePaymentBean cpb = Util.getChequePaymentBean(txnID, category,uid);
			synchronized (session) {
				session.setAttribute("chequePaymentBean", cpb);
			}
			resp.sendRedirect("/sm-admin/cheque/payment/pending");
		} else {

			resp.sendRedirect("/sm-admin/cheque-payment?category=" + category);
			return;
		}
	}

}
