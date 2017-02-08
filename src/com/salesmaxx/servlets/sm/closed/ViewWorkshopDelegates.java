package com.salesmaxx.servlets.sm.closed;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.KeyFactory;
import com.salesmaxx.beans.MyWorkshopsBean;
import com.salesmaxx.beans.ScheduleWorkshopDisplay;
import com.salesmaxx.entities.User;
import com.salesmaxx.entities.WorkShop;
import com.salesmaxx.entities.WorkshopDelegate;
import com.salesmaxx.util.Util;

public class ViewWorkshopDelegates extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4443218668742853808L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("id");
		HttpSession session = req.getSession();
		Object o = null;
		Object o1 = null;

		synchronized (session) {
			o = session.getAttribute("myWorkshopsBean");
			o1 = session.getAttribute("user");
		}

		if (Util.notNull(id) && o != null && o1 != null) {
			User u = (User) o1;
			MyWorkshopsBean mwb = (MyWorkshopsBean) o;
			List<ScheduleWorkshopDisplay> swds = mwb.getEnrolled();
			for (ScheduleWorkshopDisplay swd : swds) {
				if (swd.getId().equals(id)) {
					List<WorkshopDelegate> delegates = Util
							.getWorkshopDelegates(u.getRegId(), KeyFactory
									.createKey(WorkShop.class.getSimpleName(),
											Long.parseLong(id)));
					synchronized (session) {
						session.setAttribute("viewDelegate", swd);
						session.setAttribute("workshopDelegates", delegates);
					}
					resp.sendRedirect("/sm/closed/profile/my-workshops/delegates/view");
					break;
				}
			}
		} else {
			resp.sendRedirect("/sm/closed/profile/my-workshops");
		}
	}

}
