package com.salesmaxx.servlets.sm.closed;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.entities.Cart;
import com.salesmaxx.entities.InterswitchTransactionLog;
import com.salesmaxx.entities.User;
import com.salesmaxx.persistence.controllers.InterswitchTransactionLogController;

public class SaveInterswitchTransaction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4534516070767115678L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		Object o = null;
		Object ob = null;
		Object obj = null;
		synchronized (session) {
			o = session.getAttribute("user");
			ob = session.getAttribute("cart");
			obj = session.getAttribute("txnRef");
		}
		
		if(o != null && ob != null && obj != null) {
			User u = (User) o;
			Cart c = (Cart) ob;
			String txnRef = (String) obj;
			
			InterswitchTransactionLog itl = new InterswitchTransactionLog();
			itl.setAmount(String.valueOf(c.getSubTotal()));
			itl.setCustomerId(u.getRegId().getName());
			itl.setTnxRef(txnRef);
			
			InterswitchTransactionLogController c1 = new InterswitchTransactionLogController();
			c1.edit(itl);
		} else {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
		
		
		
	}

}
