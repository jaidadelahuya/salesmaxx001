package com.salesmaxx.servlets.sm.closed;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RestoreCart extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6098928554770614079L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		synchronized (session) {
			Object o =  session.getAttribute("savedCartState");
			session.setAttribute("cart",o); 
			
		}
		
		resp.sendRedirect(resp.encodeRedirectURL("/sm/open/cart"));
	}

}
