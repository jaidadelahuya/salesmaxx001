package com.salesmaxx.servlets.sm.open;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.beans.SignUp;
import com.salesmaxx.util.Util;

public class ResendConfirmationCode extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5226735781601609132L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		Object o = null;
		synchronized (session) {
			o = session.getAttribute("signUp");
		}
		SignUp su = null;
		if(o != null) {
			su = (SignUp) o;
		}
		
		su.setConfirmationCode(Util.generateRandomCode(100000, 999999));
		
		try {
			String body = Util.getConfirmationCodeEmailBody(su.getConfirmationCode(), su.getFirstName());
			System.out.print(su.getConfirmationCode());
			Util.sendConfirmationCodeEmail(su.getUsername(), body);
			synchronized (session) {
				session.setAttribute("signUp", su);
				session.removeAttribute("verificationError");
			}
			
			resp.sendRedirect(resp.encodeRedirectURL("/sm/open/enter-verification-code"));
		} catch (AddressException e) {
			e.printStackTrace();
			session.setAttribute("verificationError","The email address " + su.getUsername()
					+ " is either invalid or does not exist.");
			resp.sendRedirect(resp.encodeRedirectURL("/sm/open/enter-verification-code"));
			return;

		} catch (MessagingException e) {
			e.printStackTrace();
			session.setAttribute("verificationError","We could not send a confirmation code to " + su.getUsername()
					+ ". Please try again later.");
			resp.sendRedirect(resp.encodeRedirectURL("/sm/open/enter-verification-code"));
			return;
			
		}
		
		

	}

}
