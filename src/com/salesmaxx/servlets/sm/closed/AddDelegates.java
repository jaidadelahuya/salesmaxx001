package com.salesmaxx.servlets.sm.closed;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.KeyFactory;
import com.salesmaxx.beans.ScheduleWorkshopDisplay;
import com.salesmaxx.entities.User;
import com.salesmaxx.entities.WorkShop;
import com.salesmaxx.entities.WorkshopDelegate;
import com.salesmaxx.util.Util;

public class AddDelegates extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7459168269560812326L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String firstName = req.getParameter("first-name");
		String lastName = req.getParameter("last-name");
		String phone = req.getParameter("phone");
		String email = req.getParameter("email");

		HttpSession session = req.getSession();

		if (!Util.notNull(firstName)) {

			synchronized (session) {
				session.setAttribute("error",
						"You should enter the first name of this delegate.");
			}
			resp.sendRedirect("/sm/closed/profile/my-workshops/delegates/view");
			return;
		}
		synchronized (session) {
			session.setAttribute("firstName", firstName);
		}
		if (!Util.notNull(lastName)) {
			synchronized (session) {
				session.setAttribute("error",
						"You should enter the last name of this delegate.");
			}
			resp.sendRedirect("/sm/closed/profile/my-workshops/delegates/view");
			return;
		}
		
		synchronized (session) {
			session.setAttribute("lastName", firstName);
		}
		
		if (!Util.notNull(phone)) {
			synchronized (session) {
				session.setAttribute("error",
						"You should enter "+firstName+"'s phone number.");
			}
			resp.sendRedirect("/sm/closed/profile/my-workshops/delegates/view");
			return;
		}
		
		if (!Util.notNull(email)) {
			synchronized (session) {
				session.setAttribute("error",
						"You should enter "+lastName+"'s email.");
			}
			resp.sendRedirect("/sm/closed/profile/my-workshops/delegates/view");
			return;
		}
		
		

		Object o = null;
		Object o1 = null;
		Object o2 = null;
		
		synchronized (session) {
			o = session.getAttribute("user");
			o1 = session.getAttribute("viewDelegate");
			o2 = session.getAttribute("workshopDelegates");
		}

		if (o1 != null & o != null & o2 != null) {
			User u = (User) o;
			@SuppressWarnings("unchecked")
			List<WorkshopDelegate> list = (List<WorkshopDelegate>) o2;
			ScheduleWorkshopDisplay swd = (ScheduleWorkshopDisplay) o1;
			WorkshopDelegate del = new WorkshopDelegate();
			del.setEmail(email);
			del.setFirstName(firstName);
			del.setLastName(lastName);
			del.setOwnerKey(u.getRegId());
			del.setPhone(phone);
			del.setWorkshopKey(KeyFactory.createKey(
					WorkShop.class.getSimpleName(), Long.parseLong(swd.getId())));
			if (list == null) {
				list = new ArrayList<>();
			}
			list.add(del);
			synchronized (session) {
				session.setAttribute("workshopDelegates", list);
				session.setAttribute("success", firstName + " " + lastName
						+ " has been added to your list of delegates successfully.");
				session.removeAttribute("error");
				session.removeAttribute("firstName");
				session.removeAttribute("lastName");
			}
			del.persist();
			
			resp.sendRedirect("/sm/closed/profile/my-workshops/delegates/view");

		} else {
			resp.sendRedirect("/");
		}

	}

}
