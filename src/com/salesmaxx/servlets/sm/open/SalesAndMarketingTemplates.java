package com.salesmaxx.servlets.sm.open;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SalesAndMarketingTemplates extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3908098453403343882L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/sm/open/sales-and-marketing-templates.jsp");
		rd.include(req, resp);
	}

}
