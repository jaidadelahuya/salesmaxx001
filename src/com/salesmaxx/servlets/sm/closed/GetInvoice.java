package com.salesmaxx.servlets.sm.closed;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.beans.InvoiceBean;
import com.salesmaxx.beans.PurchaseHistoryBean;
import com.salesmaxx.entities.PurchaseHistory;
import com.salesmaxx.persistence.controllers.PurchaseHistoryController;
import com.salesmaxx.util.Util;

public class GetInvoice extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6644537994717695858L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		String mode = req.getParameter("mode");
		InvoiceBean ib = new InvoiceBean();
		String txn = req.getParameter("txnRef");
		if (mode != null && mode.equalsIgnoreCase("in")) {
			
			Object o = null;
			synchronized (session) {
				o = session.getAttribute("purchaseHistorys");
			}
			List<PurchaseHistoryBean> phbs = null;
			if (o != null) {
				phbs = (List<PurchaseHistoryBean>) o;

				for (PurchaseHistoryBean phb : phbs) {
					if (phb.getTxnRef().equals(txn)) {
						ib = getInvoiceBean(phb);
						break;
					}
				}
			} else {
				PurchaseHistoryController cont = new PurchaseHistoryController();
				PurchaseHistory ph = cont.findByTransactionRef(txn);
				PurchaseHistoryBean phb = Util.getPurchaseHistoryBean(ph);
				ib = getInvoiceBean(phb);
			}
		} else {
			PurchaseHistoryController cont = new PurchaseHistoryController();
			PurchaseHistory ph = cont.findByTransactionRef(txn);
			PurchaseHistoryBean phb = Util.getPurchaseHistoryBean(ph);
			ib = getInvoiceBean(phb);
		}
		synchronized (session) {
			session.setAttribute("invoice", ib);
		}
		RequestDispatcher rd = req
				.getRequestDispatcher("/WEB-INF/sm/closed/invoice.jsp");
		rd.include(req, resp);
	}
	
	private static InvoiceBean getInvoiceBean(PurchaseHistoryBean phb) {
		InvoiceBean ib = new InvoiceBean();
		ib.setDate(phb.getFormattedDate());
		ib.setItems(phb.getList());
		ib.setTxnRef(phb.getTxnRef());
		ib.setTotal(phb.getFormattedTotalPrice());
		return ib;
	}

}
