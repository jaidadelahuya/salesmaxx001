package com.salesmaxx.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class InitLoginPage extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4341550942155443732L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		synchronized (session) {
			session.removeAttribute("loginSuccess");
			session.removeAttribute("loginError");
			session.removeAttribute("loginID");
		}
		
		resp.sendRedirect(resp.encodeRedirectURL("/sm/open/login"));
		
	}

}
