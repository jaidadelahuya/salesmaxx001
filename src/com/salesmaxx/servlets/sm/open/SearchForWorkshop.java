package com.salesmaxx.servlets.sm.open;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.search.Cursor;
import com.google.appengine.api.search.QueryOptions;
import com.google.appengine.api.search.Results;
import com.google.appengine.api.search.ScoredDocument;
import com.google.appengine.api.search.SortExpression;
import com.google.appengine.api.search.SortExpression.SortDirection;
import com.google.appengine.api.search.SortOptions;
import com.salesmaxx.beans.ScheduleWorkshopDisplay;
import com.salesmaxx.beans.SearchBean;
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

		String searchString = " ";

		synchronized (session) {
			o = session.getAttribute("sb");
		}
		SearchBean sb = null;
		if (o == null) {
			sb = new SearchBean();
		} else {
			sb = (SearchBean) o;
		}

		if (Util.notNull(date)) {
			Date d = Util.WebtoDate1(date);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
			String dt = sdf.format(d);
			searchString += "date >= " + dt + " ";
			sb.setDate(date);
		}else {
			sb.setDate("");
		}

		if (Util.notNullArray(locations)) {
			Set<String> l = new HashSet<>();
			
			String ls = "location = (";
			for (int i = 0; i < locations.length; i++) {
				l.add(locations[i]);
				ls = ls.concat(locations[i]);
				if (i < locations.length - 1) {
					ls = ls.concat(" OR ");
				} else {
					ls = ls.concat(")");
				}

			}
			sb.setLocation(l);
			searchString += ls + " ";
		}else {
			sb.setLocation(null);
		}

		if (Util.notNullArray(industries)) {
			Set<String> l = new HashSet<>();
			
			String ls = "industry = (";
			for (int i = 0; i < industries.length; i++) {
				l.add(industries[i]);
				ls = ls.concat(industries[i]);
				if (i < industries.length - 1) {
					ls = ls.concat(" OR ");
				} else {
					ls = ls.concat(")");
				}

			}
			sb.setIndustry(l);
			searchString += ls + " ";
		}else {
			sb.setIndustry(null);
		}

		if (Util.notNullArray(roles)) {
			Set<String> l = new HashSet<>();
			
			String ls = "profession = (";
			for (int i = 0; i < roles.length; i++) {
				l.add(roles[i]);
				ls = ls.concat(roles[i]);
				if (i < roles.length - 1) {
					ls = ls.concat(" OR ");
				} else {
					ls = ls.concat(")");
				}

			}
			sb.setJobRole(l);
			searchString += ls + " ";
		}else {
			sb.setJobRole(null);
		}

		if (Util.notNullArray(experience)) {
			Set<String> l = new HashSet<>();
			
			String ls = "experience = (";
			for (int i = 0; i < experience.length; i++) {
				l.add(experience[i]);
				ls = ls.concat(experience[i]);
				if (i < experience.length - 1) {
					ls = ls.concat(" OR ");
				} else {
					ls = ls.concat(")");
				}

			}
			sb.setExperience(l);
			searchString += ls + " ";
		}else {
			sb.setExperience(null);
		}

		if (Util.notNullArray(format)) {
			Set<String> l = new HashSet<>();
			
			String ls = "format = (";
			for (int i = 0; i < format.length; i++) {
				l.add(format[i]);
				ls = ls.concat(format[i]);
				if (i < format.length - 1) {
					ls = ls.concat(" OR ");
				} else {
					ls = ls.concat(")");
				}

			}
			sb.setFormat(l);
			searchString += ls + " ";
		}else {
			sb.setFormat(null);
		}

		if (Util.notNullArray(type)) {
			Set<String> l = new HashSet<>();
			
			String ls = "forSale = (";
			for (int i = 0; i < type.length; i++) {
				l.add(type[i]);
				ls = ls.concat(type[i]);
				if (i < type.length - 1) {
					ls = ls.concat(" OR ");
				} else {
					ls = ls.concat(")");
				}

			}
			sb.setType(l);
			searchString += ls + " ";
		}else {
			sb.setType(null);
		}

		if (Util.notNull(expNo)) {
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

			exp = "experience = " + exp;
			searchString += exp + " ";
		}

		SortOptions sortOptions = SortOptions
				.newBuilder()
				.addSortExpression(
						SortExpression.newBuilder().setExpression("date")
								.setDirection(SortDirection.ASCENDING))
				.build();
		QueryOptions qOptions = QueryOptions
				.newBuilder()
				.setLimit(3)
				.setFieldsToReturn("workshopName", "image", "description",
						"date", "location", "workshopID", "catalogueLink")
				.setCursor(Cursor.newBuilder().build())
				.setSortOptions(sortOptions).build();
		Results<ScoredDocument> results = Util.searchIndex("WORKSHOPS",
				searchString, qOptions);
		
		List<ScheduleWorkshopDisplay> swd = Util.scoredDocumentToScheduleWorkshopDisplay(results);
		if(results.getCursor()!=null) {
			sb.setNextCursor(results.getCursor().toWebSafeString());
		}else {
			sb.setNextCursor(null);
		}
		sb.setResultsFound(results.getNumberFound());
		sb.setQueryString(searchString);
		Util.initWorkshopLayout(session, resp);
		synchronized (session) {
			session.setAttribute("sb", sb);
			session.setAttribute("workshopSchedules", swd);
		}
		RequestDispatcher rd = req
				.getRequestDispatcher("/WEB-INF/sm/open/find-workshop-by-prop.jsp");
		rd.include(req, resp);

	}

}
