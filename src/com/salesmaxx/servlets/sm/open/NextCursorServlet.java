package com.salesmaxx.servlets.sm.open;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.beans.CategoryDisplay;
import com.salesmaxx.util.Cursor;

public class NextCursorServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 126460459899613679L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String cursorName = req.getParameter("name");
		String dir = req.getParameter("dir");
		
		HttpSession session = req.getSession();
		Object o = null;
		switch (cursorName) {

		case "workshop": 
			incrementCursor("categoryDisplays", session, dir);
			resp.sendRedirect(resp.encodeRedirectURL("/sm/open/all-workshops"));
			break;
		case "related-workshops":
			incrementCursor("relatedWorkshops", session, dir);
			resp.sendRedirect(resp.encodeRedirectURL("/sm/open/show-workshop-page"));
			break;
		case "other-categories":
			incrementCursor("otherCategories", session, dir);
			resp.sendRedirect(resp.encodeRedirectURL("/sm/open/workshop-category"));
			break;
		case "workshop-category":
			incrementCursor(session, dir);
			resp.sendRedirect(resp.encodeRedirectURL("/sm/open/workshop-category"));
			break;
		case "search-doc":
			incrementCursor("searchDocs",session, dir);
			resp.sendRedirect(resp.encodeRedirectURL("/sm/open/search-page"));
			break;
		case "interswitch-log":
			incrementCursor("interswitchTransactionLog",session, dir);
			resp.sendRedirect(resp.encodeRedirectURL("/sm-admin/interswitch-transaction-log-page"));
			break;
			
		}
	}
	
	private static void incrementCursor(HttpSession session, String dir) {
		Object o = null;
		synchronized (session) {
			o = session.getAttribute("workshopCategoryDisplay");
			Cursor c = null;
			CategoryDisplay cd = null;
			if (o != null) {
				cd = (CategoryDisplay) o;
				c = cd.getCursor();
				int start = c.getStart(), end = c.getEnd();
				int step = end - start;
				if (dir.equalsIgnoreCase("next")) {
					c.setStart(start + (step + 1));
					c.setEnd(end + (step + 1));
				} else {
					c.setStart(start + (step - 1));
					c.setEnd(end + (step - 1));
				}
				
				cd.setCursor(c);
				session.setAttribute("workshopCategoryDisplay", cd);

			}

		}
		
	}
	
	private static void incrementCursor(String attrName, HttpSession session, String dir) {
		Object o = null;
		synchronized (session) {
			o = session.getAttribute(attrName);
			Cursor c = null;
			if (o != null) {
				c = (Cursor) o;
				int start = c.getStart(), end = c.getEnd();
				int step = end - start;
				if (dir.equalsIgnoreCase("next")) {
					c.setStart(start + (step + 1));
					c.setEnd(end + (step + 1));
				} else {
					c.setStart(start + (step - 1));
					c.setEnd(end + (step - 1));
				}
				session.setAttribute(attrName, c);

			}

		}
	}

}
