package com.salesmaxx.servlets.sm.open;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.util.Util;

public class WorkshopPreferenceResult extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3051269622026081791L;

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String answer = req.getParameter("answer");
		String question = req.getParameter("question");

		HttpSession session = req.getSession();
		Object o = null;
		Map<String, String> map = null;
		synchronized (session) {
			o = session.getAttribute("qaMap");
		}

		if (o != null) {
			map = (Map<String, String>) o;
			if (Util.notNull(answer) && Util.notNull(question)) {
				map.put(question, answer);

			}

			String query = "";
			int i = 1;
			for (String s : map.keySet()) {
				String a = map.get(s);
				query += s + "=" + a;
				if (map.size() > i) {
					query += "&";
				}
				i++;

			}
			resp.sendRedirect("/sm/open/search-for-workshop?" + query);

		} else {
			resp.sendRedirect("/");
		}
	}

}
