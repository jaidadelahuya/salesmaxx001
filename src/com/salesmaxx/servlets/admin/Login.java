package com.salesmaxx.servlets.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.entities.User;
import com.salesmaxx.persistence.controllers.UserController;

public class Login extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7556825940732879068L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		UserController cont = new UserController();
		User user = cont.findUserByUsername(username.trim().toLowerCase(),
				password.trim());
		HttpSession session = req.getSession();
		if (user == null) {
			resp.setContentType("text/plain");
			resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED,
					"Invalid Username/Password");

		} else {
			synchronized (session) {
				session.setAttribute("user", user);
				session.setAttribute("authenticated", true);
			}
			String url = resp.encodeURL("/sm-admin");
			resp.getWriter().write(url);

		}

	}

}
