package com.salesmaxx.servlets.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.beans.ChequeInvoice;
import com.salesmaxx.beans.ChequePaymentBean;
import com.salesmaxx.beans.ManualPaymentBean;
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
		
	
		
		HttpSession session = req.getSession();
		ChequePaymentBean ocpb = null;
		Object o = null;
		
		synchronized (session) {
			o = session.getAttribute("chequePaymentBean");
		}
		if(o != null) {
			ocpb = (ChequePaymentBean) o;
		}else {
			ocpb = new ChequePaymentBean();
		}
		if (Util.notNull(category)) {
			ocpb.setCategory(category);
		} else {
			ocpb.setCategory("pending");
		}
		
		Map<String, Object> mpb = null;
		if(ocpb.getCategory().equalsIgnoreCase(ChequeInvoice.InvoiceStatus.PENDING
				.name())) {
			mpb = Util.getChequePaymentBean(ocpb.getCategory(), ocpb.getCursor());
			List<ManualPaymentBean> l = ocpb.getMpbs();
			if(l==null) {
				l = new ArrayList<>();
			}
			l.addAll((Collection<? extends ManualPaymentBean>) mpb.get("beans"));
			ocpb.setMpbs(l);
			ocpb.setCursor((String) mpb.get("c"));
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/cheque-payment.jsp");
			rd.include(req, resp);
		}else if(ocpb.getCategory().equalsIgnoreCase(ChequeInvoice.InvoiceStatus.CLEARED
				.name())) {
			mpb = Util.getChequePaymentBean(ocpb.getCategory(), ocpb.getcCursor());
			List<ManualPaymentBean> l = ocpb.getCmpbs();
			if(l==null) {
				l = new ArrayList<>();
			}
			l.addAll((Collection<? extends ManualPaymentBean>) mpb.get("beans"));
			ocpb.setcCursor((String) mpb.get("c"));
		}else if(ocpb.getCategory().equalsIgnoreCase(ChequeInvoice.InvoiceStatus.OVERDUE
				.name())) {
			mpb = Util.getChequePaymentBean(ocpb.getCategory(), ocpb.getoCursor());
			List<ManualPaymentBean> l = ocpb.getOmpbs();
			if(l==null) {
				l = new ArrayList<>();
			}
			l.addAll((Collection<? extends ManualPaymentBean>) mpb.get("beans"));
			ocpb.setoCursor((String) mpb.get("c"));
		}
		 
		synchronized (session) {
			session.setAttribute("chequePaymentBean",ocpb);
		}
		
	}

}
