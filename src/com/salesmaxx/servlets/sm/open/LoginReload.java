package com.salesmaxx.servlets.sm.open;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.salesmaxx.entities.User;
import com.salesmaxx.persistence.controllers.UserController;
import com.salesmaxx.util.Util;

public class LoginReload extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8927787233342562211L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		HttpSession session = req.getSession();
		boolean ok = Util.notNull(username);
		if (!ok) {
			synchronized (session) {
				session.setAttribute("loginError",
						"Please enter your email or registration ID.");
			}
			resp.sendRedirect("/sm/open/login");
			return;
		}else {
			synchronized (session) {
				session.setAttribute("loginID", username);
			}
		}
		ok = Util.notNull(password);
		if (!ok) {
			synchronized (session) {
				session.setAttribute("loginError",
						"Please enter your password.");
			}
			resp.sendRedirect("/sm/open/login");
			return;
		}

		boolean usingEmail = username.contains("@");
		UserController cont = new UserController();
		User user = null;
		password = Util.toSHA256(password.trim());
		if (usingEmail) {
			user = cont.findUserByUsername(username, password);
			if (user == null) {
				synchronized (session) {
					session.setAttribute("loginError",
							"The username/password do not match.");
				}
				resp.sendRedirect("/sm/open/login");
				return;
			} else {
				synchronized (session) {
					session.removeAttribute("loginError");
				}
				Util.logUserIn(user, req, resp, false);

			}

		} else {// using reg-id

			Key key = KeyFactory.createKey(User.class.getSimpleName(), username
					.trim().toUpperCase());
			user = cont.findUser(key);
			if (user == null) {
				synchronized (session) {
					session.setAttribute("loginError", "The Registration ID "
							+ username + " does not exist.");
				}
				resp.sendRedirect("/sm/open/login");
				return;

			} else {
				if (user.getPassword().equals(password.trim())) {
					synchronized (session) {
						session.removeAttribute("loginError");
					}
					Util.logUserIn(user, req, resp, false);
				} else {
					synchronized (session) {
						session.setAttribute("loginError",
								"The username/password do not match.");
					}
					resp.sendRedirect("/sm/open/login");
					return;
				}
			}

		}
	}

}
