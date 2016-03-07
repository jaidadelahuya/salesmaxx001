package com.salesmaxx.servlets.sm.closed;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.EmbeddedEntity;
import com.google.appengine.api.datastore.Key;
import com.salesmaxx.entities.Cart;
import com.salesmaxx.entities.InterswitchTransactionLog;
import com.salesmaxx.entities.User;
import com.salesmaxx.persistence.controllers.CartController;
import com.salesmaxx.persistence.controllers.InterswitchTransactionLogController;
import com.salesmaxx.util.Util;

public class InterswitchResponse extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7081700992191063338L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String txnRef = (String) req.getAttribute("txnRef");
		String respString = (String) req.getAttribute("interswitchResponse");

		com.salesmaxx.util.InterswitchResponse ir = Util
				.parseInterswitchResponse(respString, txnRef);

		HttpSession session = req.getSession();
		Object o = null;
		Cart c = null;
		User user = null;
		int subTotal = 0;
		synchronized (session) {
			session.setAttribute("interswitch", ir);
			o = session.getAttribute("cart");
			if (ir.getResponseCode().equalsIgnoreCase("00")) {
				user = (User) session.getAttribute("user");
				String body = ClosedUtil.getEmailBody(session);
				try {
					Util.sendEmail(Util.SERVICE_ACCOUNT, user.getUsername(),
							"Your Transaction Details", body);
				} catch (AddressException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (o != null) {
					c = (Cart) o;
					subTotal = c.getSubTotal();
					c.setItems(new ArrayList<EmbeddedEntity>());
					session.setAttribute("cart", c);
				}
				CartController cc = new CartController();
				cc.edit(c);
			} else {

				if (o != null) {
					c = (Cart) o;
					subTotal = c.getSubTotal();
					session.setAttribute("cart", c);
				}
			}
		}

		InterswitchTransactionLog itl = new InterswitchTransactionLog();
		itl.setApprAmount(ir.getAmount());
		itl.setAmount(String.valueOf(subTotal));
		itl.setCustomerId(user.getRegId().getName());
		itl.setDate(ir.getTransactionDate());
		itl.setResponseCode(ir.getResponseCode());
		itl.setResponseDescription(ir.getResponseDescriptor());
		itl.setTnxRef(ir.getTxnRef());
		itl.setProductsPaidFor(new HashSet<Key>());
		InterswitchTransactionLogController c1 = new InterswitchTransactionLogController();
		// c1.create(interswitchTransactionLog, pdts);

		RequestDispatcher rd = req
				.getRequestDispatcher("/WEB-INF/sm/closed/interswitch-response.jsp");
		rd.include(req, resp);
	}

}
