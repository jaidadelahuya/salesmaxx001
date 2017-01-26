package com.salesmaxx.servlets.admin;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.beans.ChequePaymentBean;
import com.salesmaxx.beans.ManualPaymentBean;
import com.salesmaxx.entities.WorkShop;
import com.salesmaxx.util.Util;

public class ClearManualPayment extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9084085020394887432L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String txnRef = req.getParameter("txnRef");
		String id = req.getParameter("id");
		String qty = req.getParameter("qty");
		HttpSession session = req.getSession();
		if(Util.notNull(qty,txnRef,id)) {
			
			try {
				int x = Integer.parseInt(qty);
				WorkShop w = Util.getWorkshopSchedule(id);
				if((w.getTotalNumberOfSeats() - w.getNoEnrolled()) >= x) {
					boolean b = Util.clearManualPayment(txnRef,w, null,qty, false);
					if(b) {
						Object o = null;
						synchronized (session) {
							o = session.getAttribute("chequePaymentBean");
						}
						if(o!=null) {
							ChequePaymentBean cpb = (ChequePaymentBean) o;
							List<ManualPaymentBean> list = cpb.getMpbs();
							Iterator<ManualPaymentBean> it = list.iterator();
							while(it.hasNext()) {
								ManualPaymentBean mpb = it.next();
								if(mpb.getTxnRef().equals(txnRef)) {
									it.remove();
								}
							}
							cpb.setMpbs(list);
							synchronized (session) {
								session.setAttribute("chequePaymentBean", cpb);
							}
							resp.sendRedirect(resp.encodeRedirectURL("/sm-admin/cheque-payment?category=cleared"));
							return;
						}
						
						
					}
					
				} else {
					RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/reschedule-options.jsp");
					rd.include(req, resp);
				}
			}catch(NumberFormatException nfe) {
				
			}
		}
		
		
	}

}
