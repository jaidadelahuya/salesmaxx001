package com.salesmaxx.servlets.sm.open;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.beans.SignUp;
import com.salesmaxx.entities.User;
import com.salesmaxx.entities.UserGeneralInfo;
import com.salesmaxx.persistence.controllers.UserController;
import com.salesmaxx.util.Util;

public class ConfirmCode extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1166086325908626071L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String code = req.getParameter("confirmation-code");
		boolean ok = Util.notNull(code);
		HttpSession session = req.getSession();
		if (ok) {

			Object o = null;
			synchronized (session) {
				o = session.getAttribute("signUp");
			}

			if (o == null) {
				session.setAttribute(
						"verificationError",
						"We cannot verify you at this time. Please try again or Login with your social media");
				resp.sendRedirect(resp
						.encodeRedirectURL("/sm/open/enter-verification-code"));
				return;
			} else {
				SignUp su = (SignUp) o;
				if (su.getConfirmationCode().equalsIgnoreCase(code.trim())) {
					User user = Util.signUpToUser(su);
					Object oo = null;
					synchronized (session) {
						session.removeAttribute("verificationError");
						oo = session.getAttribute("fromSignUp");
					}
					if (oo == null) {
						resp.sendRedirect(resp
								.encodeRedirectURL("/sm/open/change-password-user-page"));
					} else {
						UserController cont = new UserController();
						User u = cont.createUser(user);
						if (u!=null) {
							synchronized (session) {
								session.setAttribute(
										"signUpSuccess1",
										"Your SalesMaxx account has been created successfully and you have recieved 150.00 SalesMaxx Credits. ");
								session.setAttribute("signUpSuccess2","You can login with either your username("
												+ u.getUsername()
												+ ") or your registration ID "
												+ u.getRegId().getName() );
								session.removeAttribute("fromSignUp");
							}
							resp.sendRedirect("/sm/open/sign-up-complete");
						} else {
							session.setAttribute(
									"verificationError",
									"The Verification Code your is okay but it seems we have duplicate records of your email, Please sign in with a social media or contact our support.");
							resp.sendRedirect(resp
									.encodeRedirectURL("/sm/open/enter-verification-code"));
						}

					}
					return;
				} else {
					session.setAttribute("verificationError",
							"The verification code " + code
									+ " is not correct.");
					resp.sendRedirect(resp
							.encodeRedirectURL("/sm/open/enter-verification-code"));
					return;
				}
			}
		} else {
			session.setAttribute("verificationError",
					"You have to enter the verification code sent to your email address.");
			resp.sendRedirect(resp
					.encodeRedirectURL("/sm/open/enter-verification-code"));
			return;

		}
	}

}
