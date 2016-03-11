package com.salesmaxx.servlets.sm.open;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.entities.User;
import com.salesmaxx.persistence.controllers.UserController;
import com.salesmaxx.util.Util;

public class ResetPassword extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9158999079356649615L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String pass1 = req.getParameter("pass1");
		String pass2 = req.getParameter("pass2");

		boolean ok = Util.notNull(pass1);

		if (!ok) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
					"You have to enter your new Password.");
		}

		ok = Util.notNull(pass2);

		if (!ok) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
					"You have to re-enter your new Password.");
		}

		if (pass1.equals(pass2)) {
			HttpSession session = req.getSession();
			Object o = null;
			synchronized (session) {
				o = session.getAttribute("üser");
			}
			if (o == null) {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
						"WE could not reset your password. refresh the page and try again.");
				return;
			} else {
				User u = (User) o;
				u.setPassword(Util.toSHA512(pass1));
				synchronized (session) {
					session.setAttribute("user", u);
				}
				
				UserController cont = new UserController();
				cont.edit(u);
			}
		} else {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
					"The Passwords do not match.");
		}
	}

}
