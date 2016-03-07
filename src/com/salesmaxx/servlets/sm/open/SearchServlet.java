package com.salesmaxx.servlets.sm.open;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.search.Field;
import com.google.appengine.api.search.Results;
import com.google.appengine.api.search.ScoredDocument;
import com.salesmaxx.beans.SearchBean;
import com.salesmaxx.beans.SearchDocument;
import com.salesmaxx.entities.WorkshopTemplate;
import com.salesmaxx.util.Cursor;
import com.salesmaxx.util.SearchDocumentIndexService;
import com.salesmaxx.util.Util;

public class SearchServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2330632539722529730L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String string = req.getParameter("searchString");
		
		HttpSession session = req.getSession();
		
		Results<ScoredDocument> result = SearchDocumentIndexService.retrieveDocuments(string);
		
		List<SearchDocument> list = new ArrayList<>();
	
		for(ScoredDocument sd : result) {
			SearchDocument doc = new SearchDocument();
			doc.setDescription(sd.getOnlyField("description").getText());
			doc.setWorkshopCode(sd.getOnlyField("workshopCode").getAtom());
			doc.setType(sd.getOnlyField("type").getText());
			doc.setName(sd.getOnlyField("name").getText());
			List<String> locations = new ArrayList<>();
			Iterable<Field> fs = sd.getFields("location");
			if(fs != null) {
				Iterator<Field> loc = fs.iterator();
				while(loc.hasNext()) {
					Field f = loc.next();
					if(!locations.contains(f.getText())) {
						locations.add(f.getText());
					}
				}
				doc.setLocation(locations);
			}
			
			List<String> month = new ArrayList<>();
			Iterable<Field> mm = sd.getFields("month");
			if(mm != null) {
				Iterator<Field> loc = mm.iterator();
				while(loc.hasNext()) {
					Field f = loc.next();
					if(!month.contains(f.getText())) {
						month.add(f.getText());
					}
					
				}
				doc.setMonth(month);
				list.add(doc);
			}
			
		}
		SearchBean sb = new SearchBean();
		if(list.size() > 4) {
			sb.setPageEnd(4);
		} else {
			sb.setPageEnd(list.size()-1);
		}
		sb.setPageStart(0);
		int p = 0;
		if(list.size()%5 == 0) {
			p = (int)Math.floor(list.size()/5);
		} else {
			p = (int)Math.floor(list.size()/5)+1;
		}
		
		sb.setPagination(p);
		sb.setCurrentDisplay(1);
		
		Util.initWorkshopLayout(session, resp);
		synchronized (session) {
			session.setAttribute("searchString", string);
			session.setAttribute("searchDocs",list );
			if(list.size() == 0) {
				List<WorkshopTemplate> bootcamps = Util.getWorkshopByExperience("foundation");
				session.setAttribute("bootcamps",bootcamps);
			}
			session.setAttribute("sb1", sb);
		}
		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/sm/open/search-page.jsp");
		rd.include(req, resp);
	}

}
