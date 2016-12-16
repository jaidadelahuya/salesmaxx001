package com.salesmaxx.servlets.sm.open;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class InitVerification extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8746276628239259236L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		synchronized (session) {
			session.removeAttribute("verificationError");
		}
		
		resp.sendRedirect(resp.encodeRedirectURL("/sm/open/verification"));
	}

}
