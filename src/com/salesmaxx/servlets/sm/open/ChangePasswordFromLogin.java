package com.salesmaxx.servlets.sm.open;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.entities.User;
import com.salesmaxx.persistence.controllers.UserController;
import com.salesmaxx.util.Util;

public class ChangePasswordFromLogin extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6482791505558765164L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String pass1 = req.getParameter("pass1");
		String pass2 = req.getParameter("pass2");

		HttpSession session = req.getSession();
		boolean ok = Util.notNull(pass1);
		if (!ok) {
			session.setAttribute("changePasswordError",
					"You have to enter a password of at least six characters");
			resp.sendRedirect(resp.encodeRedirectURL("/sm/open/change-password-user-page"));
			return;
		} else {
			synchronized (session) {
				session.setAttribute("pass1", pass1);
			}
			if(pass1.length() < 6) {
				session.setAttribute("changePasswordError",
						"You have to enter a password of at least six characters");
				resp.sendRedirect(resp.encodeRedirectURL("/sm/open/change-password-user-page"));
				return;
			}
		}

		ok = Util.notNull(pass2);
		if (!ok) {
			session.setAttribute("changePasswordError",
					"You have to re-type your password");
			resp.sendRedirect(resp.encodeRedirectURL("/sm/open/change-password-user-page"));
			return;
		} else {
			synchronized (session) {
				session.setAttribute("pass2", pass2);
			}
		}

		ok = pass1.equals(pass2);

		if (!ok) {
			session.setAttribute("changePasswordError",
					"The passwords do not match.");
			resp.sendRedirect(resp.encodeRedirectURL("/sm/open/change-password-user-page"));
			return;
		} else {

			Object o = null;

			synchronized (session) {
				o = session.getAttribute("user");
			}

			if (o != null) {
				User u = (User) o;

				u.setPassword(Util.toSHA256(pass1));
				UserController c = new UserController();
				c.edit(u);
				synchronized (session) {
					session.setAttribute("user", u);
					session.removeAttribute("changePasswordError");
					session.removeAttribute("pass1");
					session.removeAttribute("pass2");
					session.setAttribute("loginSuccess", "Your password has been changed successfully, you can now login");
				}
				String body = Util.getPasswordChangedMessage(u.getFirstName());
				try {
					Util.sendEmail(Util.SERVICE_ACCOUNT, u.getUsername(),
							"Your password has been changed", body);
					resp.sendRedirect(resp.encodeRedirectURL("/sm/open/login-page"));
				} catch (AddressException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}

}
