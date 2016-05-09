package com.salesmaxx.servlets.sm.closed;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.entities.CanceledWorkshop;

public class UpdateDelegates extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -355197079644144203L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		Object o = null;
	
		synchronized (session) {
			o = session.getAttribute("cancelWorkshop");
		}
		if(o==null || req.getParameter("numb")==null) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}else {
			CanceledWorkshop cw = (CanceledWorkshop) o;
			cw.setNoOfDelegates(Long.parseLong(req.getParameter("numb")));
			synchronized (session) {
				session.setAttribute("cancelWorkshop", cw);
			}
		}
	}

}
