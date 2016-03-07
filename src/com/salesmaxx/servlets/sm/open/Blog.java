package com.salesmaxx.servlets.sm.open;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Blog extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6102371908099018364L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/sm/open/blog.jsp");
		rd.include(req, resp);
	}

}
