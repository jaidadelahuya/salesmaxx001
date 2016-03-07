package com.salesmaxx.servlets.sm.open;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.KeyFactory;
import com.salesmaxx.beans.SignUp;
import com.salesmaxx.entities.User;
import com.salesmaxx.persistence.controllers.UserController;
import com.salesmaxx.util.Util;

public class ForgotPassword extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6738178158411302054L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String username = req.getParameter("username");
		String result = req.getParameter("result");
		HttpSession session = req.getSession();
		boolean ok = Util.notNull(result);
		if(!ok) {
			session.setAttribute("forgotPasswordError",
					"Please enter the result of the Captha");
			resp.sendRedirect(resp.encodeRedirectURL("/sm/open/forgot-password-page"));
			return;
		} else {
			synchronized (session) {
				Object o = session.getAttribute("capthaResult");
				if(o != null) {
					String r = (String) o;
					if(!result.equalsIgnoreCase(r)) {
						session.setAttribute("forgotPasswordError",
								"You entered a wrong result for the captha. Click reload captha");
						resp.sendRedirect(resp.encodeRedirectURL("/sm/open/forgot-password-page"));
						return;
					}
				} else {
					session.setAttribute("forgotPasswordError",
							"An error has occured. Please try again later.");
					resp.sendRedirect(resp.encodeRedirectURL("/sm/open/forgot-password-page"));
					return;
				}
				
			}
		}
		ok = Util.notNull(username);
		
		
		if (!ok) {
			session.setAttribute("forgotPasswordError",
					"Please enter your email or registration ID.");
			resp.sendRedirect(resp.encodeRedirectURL("/sm/open/forgot-password-page"));
			return;
		} else {
			boolean usingEmail = username.contains("@");
			UserController cont = new UserController();
			User user = null;
			SignUp su = null;
			if (usingEmail) {
				user = cont.findUserByUsername(username);
				if (user == null) {
					session.setAttribute("forgotPasswordError", "The email " + username
							+ " is not associated with any account.");
					resp.sendRedirect(resp.encodeRedirectURL("/sm/open/forgot-password-page"));
					return;
				} else {
					
					su = Util.toSignUpUser(user);

					String body = Util.getPasswordRecoveryEmailBody(
							su.getConfirmationCode(), su.getFirstName());
					System.out.println(su.getConfirmationCode());
					try {
						Util.sendConfirmationCodeEmail(su.getUsername(), body);
						synchronized (session) {
							session.removeAttribute("forgotPasswordError");
							session.setAttribute("signUp", su);
							session.setAttribute("user", user);
						}
						resp.sendRedirect(resp.encodeRedirectURL("/sm/open/enter-verification-code"));
						return;
					} catch (AddressException e) {
						session.setAttribute("forgotPasswordError", "We could not send an email to you at this time. Please try again later");
						resp.sendRedirect(resp.encodeRedirectURL("/sm/open/forgot-password-page"));
						return;
					} catch (MessagingException e) {
						session.setAttribute("forgotPasswordError", "We could not send an email to you at this time. Please try again later");
						resp.sendRedirect(resp.encodeRedirectURL("/sm/open/forgot-password-page"));
						return;
					}
				}
			} else {
				user = cont.findUser(KeyFactory.createKey(User.class
						.getSimpleName(), username.toUpperCase().trim()));
				if (user == null) {
					session.setAttribute("forgotPasswordError",  "The ID " + username
							+ " is not associated with any account.");
					resp.sendRedirect(resp.encodeRedirectURL("/sm/open/forgot-password-page"));
					return;
					
				} else {
				
					su = Util.toSignUpUser(user);
					System.out.println(su.getConfirmationCode());
					String body = Util.getPasswordRecoveryEmailBody(
							su.getConfirmationCode(), su.getFirstName());
					try {
						Util.sendConfirmationCodeEmail(su.getUsername(), body);
						synchronized (session) {
							session.removeAttribute("forgotPasswordError");
							session.removeAttribute("loginSuccess");
							session.setAttribute("signUp", su);
							session.setAttribute("user", user);
						}
						resp.sendRedirect(resp.encodeRedirectURL("/sm/open/enter-verification-code"));
						return;
					} catch (AddressException e) {
						session.setAttribute("forgotPasswordError", "We could not send an email to you at this time. Please try again later");
						resp.sendRedirect(resp.encodeRedirectURL("/sm/open/forgot-password-page"));
						return;
					} catch (MessagingException e) {
						session.setAttribute("forgotPasswordError", "We could not send an email to you at this time. Please try again later");
						resp.sendRedirect(resp.encodeRedirectURL("/sm/open/forgot-password-page"));
						return;
					}
				}
			}
		}
	}

}
