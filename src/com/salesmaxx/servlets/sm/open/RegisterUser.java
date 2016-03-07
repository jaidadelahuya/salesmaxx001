package com.salesmaxx.servlets.sm.open;

import java.io.IOException;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.beans.SignUp;
import com.salesmaxx.persistence.controllers.UserController;
import com.salesmaxx.util.Util;

public class RegisterUser extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4679656744100198236L;
	private static final Logger LOGGER = Logger.getLogger(RegisterUser.class
			.getName());

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String firstName = req.getParameter("first-name");
		String lastName = req.getParameter("last-name");
		String email = req.getParameter("email");
		String pass1 = req.getParameter("pass1");
		String pass2 = req.getParameter("pass2");
		
		SignUp su = new SignUp();
		su.setUsername(email.trim().toLowerCase());
		su.setPassword(pass1);
		su.setFirstName(firstName);
		su.setLastName(lastName);
		
	
		HttpSession session = req.getSession();
		synchronized (session) {
			session.setAttribute("signUp", su);
		}
		boolean ok = Util.notNull(firstName);
		if (!ok) {
			session.setAttribute("signUpError",
					"You have to enter your first Name.");
			resp.sendRedirect(resp.encodeRedirectURL("/sm/open/create-an-account"));
			return;
			
		}

		ok = Util.notNull(lastName);
		if (!ok) {
			session.setAttribute("signUpError",
					"You have to enter your last Name.");
			resp.sendRedirect(resp.encodeRedirectURL("/sm/open/create-an-account"));
			return;
			
		}

		ok = Util.notNull(email);
		if (!ok) {
			session.setAttribute("signUpError",
					"You have to enter your email address.");
			resp.sendRedirect(resp.encodeRedirectURL("/sm/open/create-an-account"));
			return;
			
		}

		ok = Util.notNull(pass1);
		if (!ok) {
			session.setAttribute("signUpError",
					"You have to enter a password with at least six characters.");
			resp.sendRedirect(resp.encodeRedirectURL("/sm/open/create-an-account"));
			return;
		} else {
			if(pass1.length() < 6) {
				session.setAttribute("signUpError",
						"You have to enter a password with at least six characters.");
				resp.sendRedirect(resp.encodeRedirectURL("/sm/open/create-an-account"));
				return;
			}
		}
		ok = pass1.equals(pass2);
		if (!ok) {
			
			session.setAttribute("signUpError",
					"The passwords do not match");
			resp.sendRedirect(resp.encodeRedirectURL("/sm/open/create-an-account"));
			return;
			
		}

		UserController cont = new UserController();
		ok = cont.userExists(email.trim().toLowerCase());
		if (ok) {
			session.setAttribute("signUpError",
					"The email " + email + " already exists. If this is your email, please login");
			resp.sendRedirect(resp.encodeRedirectURL("/sm/open/create-an-account"));
			return;
		}
		
		

		try {
			String body = Util.getConfirmationCodeEmailBody(su.getConfirmationCode(), su.getFirstName());
			Util.sendConfirmationCodeEmail(email.trim().toLowerCase(),body);
			System.out.println(su.getConfirmationCode());
			synchronized (session) {
				session.removeAttribute("signUpError");
				session.setAttribute("fromSignUp", true);
			}
			resp.sendRedirect(resp.encodeRedirectURL("/sm/open/enter-verification-code"));
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.setAttribute("signUpError",
					"The email address " + email
					+ " is either invalid or does not exist.");
			resp.sendRedirect(resp.encodeRedirectURL("/sm/open/create-an-account"));
			return;
			
		} catch (MessagingException e) {

			e.printStackTrace();
			session.setAttribute("signUpError",
					"We could not send a confirmation code to " + email
					+ ". Please try again later.");
			resp.sendRedirect(resp.encodeRedirectURL("/sm/open/create-an-account"));
			return;
			
		}

		

	}
	
		
}
