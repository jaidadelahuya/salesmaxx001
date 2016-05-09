package com.salesmaxx.servlets.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.beans.ChequePaymentBean;
import com.salesmaxx.util.Util;

public class ChequePayment extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4385019861995975538L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String category = req.getParameter("category");
		String currentPage = req.getParameter("current-page");
		String numberOfEntries = req.getParameter("no-of-entries");
	
		
		HttpSession session = req.getSession();
		ChequePaymentBean ocpb = null;
		Object o = null;
		
		synchronized (session) {
			o = session.getAttribute("chequePaymentBean");
		}
		if(o != null) {
			ocpb = (ChequePaymentBean) o;
		}
		ChequePaymentBean cpb = Util.getChequePaymentBean(category,currentPage,numberOfEntries,ocpb);
		synchronized (session) {
			session.setAttribute("chequePaymentBean",cpb);
		}
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/cheque-payment.jsp");
		rd.include(req, resp);
	}

}
