package com.salesmaxx.servlets.sm.closed;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.beans.ChequeInvoice;
import com.salesmaxx.entities.Cart;
import com.salesmaxx.entities.User;

public class InitChequePayment extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5634518641144870425L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		Object o = null;
		Object o1 = null;
		synchronized (session) {
			o = session.getAttribute("user");
			o1 = session.getAttribute("cart");
		}
		if (o != null && o1 != null) {
			Cart c = (Cart) o1;
			User u = (User) o;
			ChequeInvoice cq = new ChequeInvoice();
			SimpleDateFormat sm = new SimpleDateFormat("E, dd MMM yyyy");
			cq.setDate(sm.format(new Date()));
			Calendar date = Calendar.getInstance();
		    date.setTime(new Date());
		    date.add(Calendar.DAY_OF_MONTH,5);
		    if(date.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
		    	date.add(Calendar.DAY_OF_MONTH, 2);
		    } else if(date.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
		    	date.add(Calendar.DAY_OF_MONTH, 1);
		    }
		    cq.setItems(c.getCartItems());
		    cq.setDueDate(sm.format(date.getTime()));
			if(u.getUsername() == null) {
				if(u.getEmails() != null && !u.getEmails().isEmpty()) {
					for(String s : u.getEmails()) {
						cq.setEmail(s);
					}
				}
			} else {
				cq.setEmail(u.getUsername());
			}

			//cq.setItems(items);

			cq.setName(u.getFirstName()+" "+u.getLastName());
			cq.setSubTotal(c.getFormattedsubTotal());
			cq.setTxnRef(ClosedUtil.getTransactionRef(u));
			
			synchronized (session) {
				session.setAttribute("chequeInvoice", cq);
			}
			
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/sm/closed/cheque-invoice.jsp");
			rd.forward(req, resp);
		}
		
		
		
	}

}
