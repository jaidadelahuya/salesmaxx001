package com.salesmaxx.servlets.sm.open;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class InitSignUp extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3449677477532778817L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		synchronized (session) {
			session.removeAttribute("signUpError");
		}
		
		resp.sendRedirect(resp.encodeRedirectURL("/sm/open/account/new"));
	}

}
