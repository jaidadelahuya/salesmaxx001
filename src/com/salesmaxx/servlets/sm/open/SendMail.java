package com.salesmaxx.servlets.sm.open;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.salesmaxx.entities.ContactForm;
import com.salesmaxx.persistence.controllers.ContactFormController;
import com.salesmaxx.util.Util;

public class SendMail extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5922682007017161463L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		String msg = req.getParameter("msg");
		
		boolean ok = Util.notNull(name,email,msg);
		
		ContactForm cf = Util.createContactForm(name,email,phone,msg);
		ContactFormController cfc =new ContactFormController();
		cfc.create(cf);
		
		if(!ok) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "bad request");
		}
		
		String message = Util.createEmailMessage(name,email,phone,msg);
		
		try {
			Util.sendEmail("salesmaxx001@appspot.gserviceaccount.com", "stephenu@profiliant.com", "Message from SalesMaxx.com", message);
		} catch (AddressException e) {
			resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED,"could not send");
		} catch (MessagingException e) {
			resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED,"could not send");
		}
	}

}
