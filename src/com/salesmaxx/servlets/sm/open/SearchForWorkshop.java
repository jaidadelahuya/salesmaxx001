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

public class SearchForWorkshop extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8102499969253332830L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String date = req.getParameter("date");
		String[] locations = req.getParameterValues("location");
		String[] industries = req.getParameterValues("industry");
		String[] roles = req.getParameterValues("job-role");
		String[] experience = req.getParameterValues("experience");
		String[] format = req.getParameterValues("format");
		String[] type = req.getParameterValues("type");
		String expNo = req.getParameter("experience-no");
		
		HttpSession session = req.getSession(false);
		Object o = null;
		
		String searchString  = "";
		
		synchronized (session) {
			o = session.getAttribute("searchBean");
		}
		SearchBean sb = null;
		if(o==null) {
			sb = new SearchBean();
		} else {
			sb = (SearchBean) o;
		}

		List<WorkshopTemplate> temps = null;
		

		boolean ok = Util.notNullArray(industries);
		if (ok) {
			temps = Util.getWorkshopByIndustry(industries);
			Set<String> i = sb.getIndustry();
			if(i==null) {
				i = new HashSet<>();
			}
			for(String s: industries) {
				if(s.startsWith("-")){
					s = s.replace("-", "").trim();
					i.remove(s);
				}else {
					i.add(s);
				}
			}
			
			sb.setIndustry(i);
		}

		ok = Util.notNullArray(experience);
		if (ok) {
			temps = Util.getWorkshopByExperience(temps, experience);
			Set<String> i = sb.getExperience();
			if(i==null) {
				i = new HashSet<>();
			}
			for(String s: experience) {
				if(s.startsWith("-")){
					s = s.replace("-", "").trim();
					i.remove(s);
				}else {
					i.add(s);
				}
			}
			sb.setExperience(i);
		}
		
		ok = Util.notNull(expNo);
		if (ok) {
			int i = 0;
			try {
				i = Integer.parseInt(expNo);

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
			sb.setExperience(set);
		}

		ok = Util.notNullArray(roles);
		if (ok) {
			temps = Util.getWorkshopByJobRole(temps, roles);
			Set<String> i = sb.getJobRole();
			if(i==null) {
				i = new HashSet<>();
			}
			for(String s: roles) {
				if(s.startsWith("-")){
					s = s.replace("-", "").trim();
					i.remove(s);
				}else {
					i.add(s);
				}
			}
			sb.setJobRole(i);
		}

		List<WorkShop> list = Util.getAllScheduleWorkshopFromTemplate(temps);

		ok = Util.notNullArray(locations);
		if (ok) {
			list = Util.findWorkshopByLocation(list, locations);
			Set<String> i = sb.getLocation();
			if(i==null) {
				i = new HashSet<>();
			}
			for(String s: locations) {
				if(s.startsWith("-")){
					s = s.replace("-", "").trim();
					i.remove(s);
				}else {
					i.add(s);
				}
			}
			sb.setLocation(i);
		}

		ok = Util.notNull(date);

		if (ok) {

			Date d = Util.WebtoDate1(date);
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
			sb.setDate(date);
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
			Set<String> i = sb.getType();
			if(i==null) {
				i = new HashSet<>();
			}
			for(String s: type) {
				if(s.startsWith("-")){
					s = s.replace("-", "").trim();
					i.remove(s);
				}else {
					i.add(s);
				}
			}
			sb.setType(i);
		}

		ok = Util.notNullArray(format);
		if (ok) {
			if (format.length == 1) {
				for (String s : format) {

					if (s.equalsIgnoreCase("instructor-led")) {
						list = Util.getInstructorLedWorkshops(list);
					} else if (s.equalsIgnoreCase("online")) {
						list = Util.getOnlineWorkshops(list);
					}
				}
			}
			
			Set<String> i = sb.getFormat();
			if(i==null) {
				i = new HashSet<>();
			}
			for(String s: format) {
				if(s.startsWith("-")){
					s = s.replace("-", "").trim();
					i.remove(s);
				}else {
					i.add(s);
				}
			}
			sb.setFormat(i);
		}

		List<ScheduleWorkshopDisplay> swds = Util
				.toScheduleWorkshopDisplay(list);
		
		Util.initWorkshopLayout(session, resp);
		
		Set<ScheduleWorkshopDisplay> swdss = new HashSet<>();
		swdss.addAll(swds);
		if(swdss.size() > 3) {
			sb.setPageEnd(3);
		} else {
			sb.setPageEnd(swdss.size()-1);
		}
		sb.setPageStart(0);
		int p = 0;
		if(swdss.size()%4 == 0) {
			p = (int)Math.floor(swdss.size()/4);
		} else {
			p = (int)Math.floor(swdss.size()/4)+1;
		}
		
		sb.setPagination(p);
		sb.setCurrentDisplay(1);
		synchronized (session) {
			session.setAttribute("workshopSchedules", swdss);
			session.setAttribute("searchBean", sb);
			session.setAttribute("searchBeanX", sb);
			
		}
		
		

		RequestDispatcher rd = req
				.getRequestDispatcher("/WEB-INF/sm/open/find-workshop-by-prop.jsp");
		rd.include(req, resp);

	}

	

	
}
