package com.salesmaxx.servlets.sm.open;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.beans.ScheduleWorkshopDisplay;
import com.salesmaxx.beans.SearchBean;
import com.salesmaxx.entities.WorkShop;
import com.salesmaxx.entities.WorkshopTemplate;
import com.salesmaxx.util.Util;

public class FindWorkshop extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8902586025149585092L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String industry = req.getParameter("industry");
		String experience = req.getParameter("experience");
		String role = req.getParameter("role");
		String location = req.getParameter("location");
		String date = req.getParameter("date");
		String[] type = req.getParameterValues("type");
		SearchBean bean = new SearchBean();

		if (!(Util.notNull(industry) | Util.notNull(experience)
				| Util.notNull(role) | Util.notNull(location)
				| Util.notNull(date) | Util.notNullArray(type))) {
			req.setAttribute("indexWorkshopFinderError", true);
			req.setAttribute("indexWorkshopFinderErrorMsg",
					"You have to complete at least one field.");
			RequestDispatcher rd = req.getRequestDispatcher("/index");
			rd.include(req, resp);
			return;
		}

		List<WorkshopTemplate> temps = null;

		boolean ok = Util.notNull(industry);
		if (ok) {
			temps = Util.getWorkshopByIndustry(industry);
			Set<String> set = new HashSet<>();
			set.add(industry);
			bean.setIndustry(set);
		}

		ok = Util.notNull(experience);
		if (ok) {
			int i = 0;
			try {
				i = Integer.parseInt(experience);

			} catch (Exception e) {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
						"Enter a valid integer for years of experience.");
			}

			String exp = null;
			if (i < 4) {
				exp = "Foundation";
			} else if (i < 11) {
				exp = "Intermediate";
			} else if (i > 10) {
				exp = "Advanced";
			}

			temps = Util.getWorkshopByExperience(exp, temps);
			Set<String> set = new HashSet<>();
			set.add(exp);
			bean.setExperience(set);
		}

		ok = Util.notNull(role);
		if (ok) {
			temps = Util.getWorkshopByJobRole(role, temps);
			Set<String> set = new HashSet<>();
			set.add(role);
			bean.setJobRole(set);
		}

		List<WorkShop> list = Util.getAllScheduleWorkshopFromTemplate(temps);
		ok = Util.notNull(location);
		if (ok) {
			list = Util.findWorkshopByLocation(location, list);
			Set<String> set = new HashSet<>();
			set.add(location);
			bean.setLocation(set);
		}

		ok = Util.notNull(date);

		if (ok) {

			Date d = Util.WebtoDate(date);
			List<WorkShop> nl = new ArrayList<>();
			for (WorkShop w : list) {
				if (w.getStartDate().equals(d)) {
					nl.add(w);
				}
			}

			Calendar c = new GregorianCalendar();
			c.setTime(d);
			for (WorkShop w : list) {
				Calendar c1 = new GregorianCalendar();
				c1.setTime(w.getStartDate());
				if (c.get(Calendar.MONTH) == c1.get(Calendar.MONTH)
						&& c.get(Calendar.YEAR) == c1.get(Calendar.YEAR)) {
					nl.add(w);
				}
			}

			list = nl;
			bean.setDate(date);
		}

		ok = Util.notNullArray(type);
		if (ok) {
			if (type.length == 1) {
				for (String s : type) {

					if (s.equalsIgnoreCase("free")) {
						list = Util.getFreeWorkshops(list);
					} else if (s.equalsIgnoreCase("paid")) {
						list = Util.getPaidWorkshops(list);
					}
				}
			}
			
			Set<String> set = new HashSet<>();
			set.addAll(Arrays.asList(type));
			bean.setType(set);

		}

		List<ScheduleWorkshopDisplay> swds = Util
				.toScheduleWorkshopDisplay(list);
		HttpSession session = req.getSession();
		Util.initWorkshopLayout(session, resp);

		synchronized (session) {
			session.setAttribute("workshopSchedules", swds);
			session.setAttribute("searchBean", bean);
			session.setAttribute("searchBeanX", bean);
		}

		RequestDispatcher rd = req
				.getRequestDispatcher("/WEB-INF/sm/open/find-workshop-by-prop.jsp");
		rd.include(req, resp);

	}
}
