package com.salesmaxx.servlets.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.beans.ScheduleWorkshopDisplay;
import com.salesmaxx.entities.WorkShop;
import com.salesmaxx.persistence.controllers.WorkshopController;
import com.salesmaxx.util.Util;

public class ExtendClass extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9206774980932387895L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("id");
		HttpSession session = req.getSession();
		if(Util.notNull(id)) {
			WorkShop w = Util.getWorkshopSchedule(id);
			if(w.getTotalNumberOfSeats()>=30) {
				synchronized (session) {
					session.setAttribute("errorMsg", "The workshop class size cannot be increased above 30");
					
				}
			}else {
				w.setTotalNumberOfSeats(30);
				WorkshopController c = new WorkshopController();
				List<WorkShop> l = new ArrayList<>();
				l.add(w);
				c.edit(l);
				Object o = null;
				synchronized (session) {
					o = session.getAttribute("extendWorkshopList");
				}
				@SuppressWarnings("unchecked")
				List<ScheduleWorkshopDisplay> sch = (List<ScheduleWorkshopDisplay>) o;
				for(ScheduleWorkshopDisplay s : sch) {
					if(s.getId().equals(String.valueOf(w.getId().getId()))) {
						s.setTotalSeats(30);
						s.setSeatsLeft(s.getSeatsLeft()+5);
					}
				}
				synchronized (session) {
					session.setAttribute("successMsg", "The workshop class size has been increased to 30");
					session.setAttribute("extendWorkshopList", sch);
				}
				
				
			}
		}
		resp.sendRedirect(resp.encodeRedirectURL("/sm-admin/workshop/2/extend"));
	}

}
