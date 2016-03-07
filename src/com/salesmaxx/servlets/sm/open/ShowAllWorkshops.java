package com.salesmaxx.servlets.sm.open;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.salesmaxx.beans.CategoryDisplay;
import com.salesmaxx.beans.SubNavItem;
import com.salesmaxx.beans.WorkshopSideBarItem;
import com.salesmaxx.entities.Industry;
import com.salesmaxx.entities.JobRole;
import com.salesmaxx.entities.SkillLevel;
import com.salesmaxx.entities.WorkshopTemplate;
import com.salesmaxx.persistence.controllers.IndustryController;
import com.salesmaxx.persistence.controllers.JobRoleController;
import com.salesmaxx.persistence.controllers.WorkshopTemplateController;
import com.salesmaxx.util.Cursor;
import com.salesmaxx.util.Util;

public class ShowAllWorkshops extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3808843034081010555L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
	
		
		List<WorkshopTemplate> workshops = Util.getWorkshopTemplateFromCache();
				
		//process content
		Set<CategoryDisplay> cds = Util.getCategoryDisplays(workshops); 
		List lcds = new ArrayList();   
		lcds.addAll(cds);
		Cursor cursor = new Cursor(lcds, 5, 0, 0);
		// save to session
		HttpSession session = req.getSession();
		synchronized (session) {

			session.setAttribute("categoryDisplays", cursor);
			session.setAttribute("cds", cds);
		}
		
		Util.initWorkshopLayout(session, resp);
		
		Util.WORKSHOP_CACHE.put("categoryDisplays", cds);
		
		RequestDispatcher rd = req.getRequestDispatcher("/sm/open/all-workshops");
		rd.include(req, resp);
	}

	
	


}
