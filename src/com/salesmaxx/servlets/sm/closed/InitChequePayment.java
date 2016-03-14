package com.salesmaxx.servlets.sm.closed;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.beans.ChequeInvoice;
import com.salesmaxx.entities.Cart;
import com.salesmaxx.entities.ManualTransaction;
import com.salesmaxx.entities.User;
import com.salesmaxx.persistence.controllers.ManualTransactionController;
import com.salesmaxx.util.Util;

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
		Date issueDate = new Date();
		if (o != null && o1 != null) {
			Cart c = (Cart) o1;
			User u = (User) o;
			ChequeInvoice cq = new ChequeInvoice();
			SimpleDateFormat sm = new SimpleDateFormat("E, dd MMM yyyy");
			cq.setDate(sm.format(issueDate));
			Calendar date = Calendar.getInstance();
			date.setTime(issueDate);
			date.add(Calendar.DAY_OF_MONTH, 5);
			if (date.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
				date.add(Calendar.DAY_OF_MONTH, 2);
			} else if (date.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				date.add(Calendar.DAY_OF_MONTH, 1);
			}
			cq.setItems(c.getCartItems());
			cq.setDueDate(sm.format(date.getTime()));
			if (u.getUsername() == null) {
				if (u.getEmails() != null && !u.getEmails().isEmpty()) {
					for (String s : u.getEmails()) {
						cq.setEmail(s);
					}
				}
			} else {
				cq.setEmail(u.getUsername());
			}

			// cq.setItems(items);

			cq.setName(u.getFirstName() + " " + u.getLastName());
			cq.setSubTotal(c.getFormattedsubTotal());
			cq.setTxnRef(ClosedUtil.getTransactionRef(u));

			ManualTransaction mt = new ManualTransaction();
			mt.setIssueDate(issueDate);
			mt.setItems(c.getItems());
			mt.setOwnerKey(u.getRegId());
			mt.setStatus(ChequeInvoice.InvoiceStatus.PENDING.name());
			mt.setTxnRef(cq.getTxnRef());
			mt.setTransactionType(ManualTransaction.TransactionType.CHEQUE
					.name());

			ManualTransactionController c1 = new ManualTransactionController();
			c1.create(mt);

			synchronized (session) {
				session.setAttribute("chequeInvoice", cq);
			}

			String body = "<p>Hello,</p>"
					+ "<p>An invoice has been created for a transaction with ref "
					+ mt.getTxnRef()
					+ ".</p>"
					+ "<p><a href='https://salesmaxx001.appspot.com/sm/open/get-cheque-invoice?admin=admin&txnRef="
					+ mt.getTxnRef() + "'>Click here</a> to view</p>"+"<p>SalesMaxx<p>";
			String title = "New Invoice created: Transaction Ref "+mt.getTxnRef();
			String to = "stephen_ubogu@outlook.com";
			try {
				Util.sendEmail(Util.SERVICE_ACCOUNT, to, title, body);
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			RequestDispatcher rd = req
					.getRequestDispatcher("/WEB-INF/sm/closed/cheque-invoice.jsp");
			rd.forward(req, resp);
		}

	}

}
