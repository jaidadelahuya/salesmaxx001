package com.salesmaxx.servlets.sm.open;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.beans.SearchBean;
import com.salesmaxx.util.Util;

public class NextPageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7169079609669534672L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		String name = req.getParameter("name");
		
		HttpSession session = req.getSession(false);
		if(name.equalsIgnoreCase("workshop-finder")) {
			Util.initWorkshopFinderPagination(req,resp);
		} else if(name.equalsIgnoreCase("search-page")) {
			Util.initSearchPagePagination(req,resp);
		} else if(name.equalsIgnoreCase("interswitch-log")) {
			Util.initInterswitchLogPagination(req,resp);
		}



	}

}
