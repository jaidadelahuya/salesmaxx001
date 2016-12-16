package com.salesmaxx.servlets.admin;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.beans.AdminWorkshopPageBean;
import com.salesmaxx.beans.WorkshopTemplateBean;
import com.salesmaxx.util.Util;

public class ShowAllWorkshops extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5330718697806592570L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String sort = req.getParameter("sort");
		AdminWorkshopPageBean awpb = new AdminWorkshopPageBean();
		
			List<WorkshopTemplateBean> wtbs = Util.getAllWorkshopTemplateBeans(); 
			awpb.setWorkshopTemplate(wtbs);
			if(sort.equalsIgnoreCase("name")) {
				awpb.setSort("name");
				Comparator<WorkshopTemplateBean> c = new Comparator<WorkshopTemplateBean>() {
					@Override
					public int compare(WorkshopTemplateBean o1,
							WorkshopTemplateBean o2) {
						return o1.getName().compareTo(o2.getName());
					}
				};
				Collections.sort(awpb.getWorkshopTemplate(), c);
			} else if(sort.equalsIgnoreCase("month")) {
				awpb.setSort("month");
			} else if(sort.equalsIgnoreCase("city")) {
				awpb.setSort("city");
			}
			awpb.setModule("all");
			
		HttpSession session = req.getSession();
		synchronized (session) {
			session.setAttribute("workshop", awpb);
		}
		req.getRequestDispatcher("/WEB-INF/all-workshops.jsp").include(req, resp);
	}

}
