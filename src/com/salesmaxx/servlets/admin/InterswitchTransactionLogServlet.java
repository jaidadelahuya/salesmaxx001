package com.salesmaxx.servlets.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.beans.SearchBean;
import com.salesmaxx.entities.InterswitchTransactionLog;
import com.salesmaxx.persistence.controllers.InterswitchTransactionLogController;
import com.salesmaxx.util.Cursor;

public class InterswitchTransactionLogServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6612017026142204067L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		InterswitchTransactionLogController c = new InterswitchTransactionLogController();
		List<InterswitchTransactionLog> list = c.findAll();
		HttpSession session = req.getSession();
		
		SearchBean sb = new SearchBean();
		if(list.size() > 9) {
			sb.setPageEnd(9);
		} else {
			sb.setPageEnd(list.size()-1);
		}
		sb.setPageStart(0);
		int p = 0;
		if(list.size()%9 == 0) {
			p = (int)Math.floor(list.size()/9);
		} else {
			p = (int)Math.floor(list.size()/9)+1;
		}
		
		sb.setPagination(p);
		sb.setCurrentDisplay(1);
		synchronized (session) {
			session.setAttribute("interswitchTransactionLog", list);
			session.setAttribute("sb", sb);
		}
		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/interswitch-transaction-log.jsp");
		rd.include(req, resp);
		
	}

}
