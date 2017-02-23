package com.salesmaxx.servlets.sm.closed;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Transaction;
import com.salesmaxx.beans.ScheduleWorkshopDisplay;
import com.salesmaxx.entities.User;
import com.salesmaxx.entities.WorkShop;
import com.salesmaxx.entities.WorkshopDelegate;
import com.salesmaxx.persistence.controllers.EMF;

public class DeleteDelegate extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1352457118431047919L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("id");
		
		Object o = null;
		Object o1 = null;
		Object o2 = null;
		HttpSession session = req.getSession();
		synchronized (session) {
			o = session.getAttribute("user");
			o1 = session.getAttribute("viewDelegate");
			o2 = session.getAttribute("workshopDelegates");
		}
		
		if (o1 != null & o != null & o2 != null) {
			User u = (User) o;
			@SuppressWarnings("unchecked")
			List<WorkshopDelegate> list = (List<WorkshopDelegate>) o2;
			
			
			Iterator<WorkshopDelegate> it = list.iterator();
			while(it.hasNext()) {
				WorkshopDelegate wd = it.next();
				if(wd.getId().equals(KeyFactory.stringToKey(id))) {
					it.remove();
					synchronized (session) {
						session.setAttribute("workshopDelegates", list);
						session.setAttribute("success!", wd.getFirstName() + " " + wd.getLastName()
								+ " has been removed from your list of delegates successfully.");
						Transaction txn = EMF.getDs().beginTransaction();
						EMF.getDs().delete( wd.getId());
						txn.commitAsync();
					}
					
					break;
				}
				
				
			}
			
			resp.sendRedirect("/sm/closed/profile/my-workshops/delegates/view");

		} else {
			resp.sendRedirect("/");
		}
	}

}
