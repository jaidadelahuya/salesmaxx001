package com.salesmaxx.servlets.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.salesmaxx.properties.Messages;

public class CreateUser extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7885412998754443078L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher rd = req
				.getRequestDispatcher("/WEB-INF/create-user.jsp"); //$NON-NLS-1$
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String password = req.getParameter(Messages
				.getString("CreateUser.password")); //$NON-NLS-1$
		if (password.equalsIgnoreCase(Messages
				.getString("CreateUser.developerPassword"))) { //$NON-NLS-1$
			req.getSession().setAttribute(
					Messages.getString("CreateUser.authenticated"), true); //$NON-NLS-1$
			resp.getWriter().write("/create-user-page");

		}

	}
}
