package com.salesmaxx.servlets.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.salesmaxx.entities.WorkShop;
import com.salesmaxx.persistence.controllers.ManualTransactionController;
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
		
		if(Util.notNull(qty,txnRef,id)) {
			
			try {
				int x = Integer.parseInt(qty);
				WorkShop w = Util.getWorkshopSchedule(id);
				if((w.getTotalNumberOfSeats() - w.getNoEnrolled()) >= x) {
					boolean b = Util.clearManualPayment(txnRef,w, null,qty, false);
					resp.sendRedirect(resp.encodeRedirectURL("/sm-admin/cheque-payment?category=cleared"));
				} else {
					RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/reschedule-options.jsp");
					rd.include(req, resp);
				}
			}catch(NumberFormatException nfe) {
				
			}
		}
		
		
	}

}
