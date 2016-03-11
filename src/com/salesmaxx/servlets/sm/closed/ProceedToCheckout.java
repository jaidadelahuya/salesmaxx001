package com.salesmaxx.servlets.sm.closed;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.beans.ChequeInvoice;
import com.salesmaxx.entities.Cart;
import com.salesmaxx.entities.User;

public class ProceedToCheckout extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2253265791651351868L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String payMethod = req.getParameter("pay-method");
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

			if (payMethod.equals("web-pay")) {
				String redirectUrl = resp
						.encodeURL("https://salesmaxx001.appspot.com/sm/closed/interswitch-callback");
				String txnRef = ClosedUtil.getTransactionRef(u);
				String interHash = ClosedUtil.getInterswitchHash(txnRef,
						String.valueOf(c.getSubTotal() * 100), redirectUrl);

				synchronized (session) {
					session.setAttribute("txnRef", txnRef);
					session.setAttribute("interHash", interHash);
					session.setAttribute("interRedirectUrl", redirectUrl);
					session.setAttribute("payMethod", payMethod);
				}
				String url = resp.encodeURL("/sm/closed/make-payment");
				resp.sendRedirect(url);
			} else if (payMethod.equals("cheque") | payMethod.equals("e-transfer") ) {
				session.setAttribute("payMethod", payMethod);
				String url = resp.encodeURL("/sm/closed/make-payment");
				resp.sendRedirect(url);
			} 
			
		}

	}

}

