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

import com.google.appengine.api.search.Cursor;
import com.google.appengine.api.search.QueryOptions;
import com.google.appengine.api.search.Results;
import com.google.appengine.api.search.ScoredDocument;
import com.google.appengine.api.search.SortExpression;
import com.google.appengine.api.search.SortExpression.SortDirection;
import com.google.appengine.api.search.SortOptions;
import com.google.gson.Gson;
import com.salesmaxx.beans.ScheduleWorkshopDisplay;
import com.salesmaxx.beans.SearchBean;
import com.salesmaxx.util.Util;

public class WorkshopSearchNextResult extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4400678041008667398L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		Object o = null;
		synchronized (session) {
			o = session.getAttribute("sb");
		}
		if(o!=null && ((SearchBean)o).getNextCursor()!=null) {
			SearchBean sb = (SearchBean) o;
			SortOptions sortOptions = SortOptions
					.newBuilder()
					.addSortExpression(
							SortExpression.newBuilder().setExpression("date")
									.setDirection(SortDirection.ASCENDING))
					.build();
			QueryOptions qOptions = QueryOptions
					.newBuilder()
					.setLimit(10)
					.setFieldsToReturn("workshopName", "image", "description",
							"date", "location", "workshopID", "catalogueLink")
					.setCursor(Cursor.newBuilder().build(sb.getNextCursor()))
					.setSortOptions(sortOptions).build();
			Results<ScoredDocument> results = Util.searchIndex("wk",
					sb.getQueryString(), qOptions);
			
			List<ScheduleWorkshopDisplay> swd = Util.scoredDocumentToScheduleWorkshopDisplay(results);
			if(results.getCursor()!=null) {
				sb.setNextCursor(results.getCursor().toWebSafeString());
			}else {
				sb.setNextCursor(null);
			}
	
			synchronized (session) {
				List<ScheduleWorkshopDisplay> l = (List<ScheduleWorkshopDisplay>) session.getAttribute("workshopSchedules");
				l.addAll(swd);
				session.setAttribute("sb", sb);
				session.setAttribute("workshopSchedules", l);
			}
			String json = new Gson().toJson(swd);
			resp.setContentType("application/json");
			resp.getWriter().write(json);
		}
	}

}
