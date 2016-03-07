package com.salesmaxx.servlets.sm.open;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.beans.ScheduleWorkshopDisplay;
import com.salesmaxx.entities.Category;
import com.salesmaxx.entities.Review;
import com.salesmaxx.entities.WorkShop;
import com.salesmaxx.entities.WorkshopTemplate;
import com.salesmaxx.util.Cursor;
import com.salesmaxx.util.Util;

public class ShowWorkshop extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5140791054841476650L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("id");
		boolean ok = Util.notNull(id);
		if (ok) {
			List<WorkshopTemplate> workshops = Util
					.getWorkshopTemplateFromCache();
			WorkshopTemplate wst = Util.getWorkshopFromList(id, workshops);
			if (wst != null) {
				List<WorkShop> schedules = Util.getScheduledWorkshops(wst
						.getSchedules());

				List<ScheduleWorkshopDisplay> sch = Util
						.toScheduleWorkshopDisplay(schedules);
				Category cat = Util.getWorkshopCategory(wst.getWorkshopId()
						.getName());
				List<Review> reviews = Util.getReviews(wst.getReviews());
				Double rating = Util.getAvgRating(reviews);
				List<WorkshopTemplate> relatedWorkshops = Util.getWorkshopTemplates(wst.getRelatedWorkshops());
		
				HttpSession session = req.getSession();
				synchronized (session) {
					session.setAttribute("relatedWorkshops", relatedWorkshops);
					session.setAttribute("workshopTemplate", wst);
					session.setAttribute("workshopSchedules", sch);
					session.setAttribute("workshopCategory", cat);
					session.setAttribute("reviews", reviews);
					session.setAttribute("rating", rating);
				}

				Util.initWorkshopLayout(session, resp);
				RequestDispatcher rd = req
						.getRequestDispatcher("/WEB-INF/sm/open/workshop-information.jsp");
				rd.forward(req, resp);
			}

		} else {
			// goto error page
		}

	}
}
