package com.salesmaxx.servlets.sm.open;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.beans.WorkshopDisplay;
import com.salesmaxx.entities.WorkshopTemplate;
import com.salesmaxx.util.Util;

public class GetWorkshopByJobRole extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6368279621150800333L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String jobRole = req.getParameter("job-role");
		boolean ok = Util.notNull(jobRole);
		if(ok) {
			
			List<WorkshopTemplate> temps = Util.getWorkshopByJobRole(jobRole);
			List<WorkshopDisplay> wds = Util.toWorkshopDisplay(temps);
			HttpSession session  = req.getSession();
			Util.initWorkshopLayout(session, resp);
			synchronized (session) {
				session.setAttribute("workshopDisplays", wds);
				session.setAttribute("jobRole", jobRole.toUpperCase());
			}
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/sm/open/workshops-by-job-role.jsp");
			rd.forward(req, resp);
		}
	}

}
