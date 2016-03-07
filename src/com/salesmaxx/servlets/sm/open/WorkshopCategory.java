package com.salesmaxx.servlets.sm.open;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.beans.CategoryDisplay;
import com.salesmaxx.beans.OtherCategoriesDisplay;
import com.salesmaxx.beans.SubNavItem;
import com.salesmaxx.entities.WorkshopTemplate;
import com.salesmaxx.util.Cursor;
import com.salesmaxx.util.Util;

public class WorkshopCategory extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3912962427318253083L;

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String categoryName = req.getParameter("categoryName");
		categoryName = categoryName.replace("--and", "&");

		HttpSession session = req.getSession();
		CategoryDisplay c = null;
		Set<CategoryDisplay> cds = null;
		Object o = session.getAttribute("cds");
		if (o == null) {
			List<WorkshopTemplate> workshops = Util.getWorkshopTemplateFromCache();
			cds = Util.getCategoryDisplays(workshops);
		} else {
			cds = (HashSet<CategoryDisplay>) o;
		}
		
		for (CategoryDisplay cd : cds) {
			if (cd.getCategoryName().equalsIgnoreCase(categoryName)) {
				c = cd;
				break;
			}

		}
		
		List<OtherCategoriesDisplay> ocds = Util.getOtherCategories(categoryName);
		synchronized (session) {
	
			session.setAttribute("workshopCategoryDisplay", c);
			session.setAttribute("otherCategories", ocds);
		}
		Util.initWorkshopLayout(session, resp);
		RequestDispatcher rd = req
				.getRequestDispatcher("/sm/open/workshop-category");
		rd.include(req, resp);
	}

	
}
