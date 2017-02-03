package com.salesmaxx.servlets.sm.open;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.util.ProspectUtil;
import com.salesmaxx.util.Util;



public class CrazyServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9097261650265896919L;
	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String group = req.getParameter("group");
		String answer = req.getParameter("answer");
		String question = req.getParameter("question");
	
		HttpSession session = req.getSession();
		Object o = null;
		Map<String, String> map = null;
		synchronized (session) {
			o = session.getAttribute("qaMap");
		}
		if(o==null) { 
			map = new HashMap<String, String>();
		}else {
			map = (Map<String, String>) o;
		}
		
		if(Util.notNull(answer) && Util.notNull(question)) {
			map.put(question, answer);
			synchronized (session) {
				session.setAttribute("qaMap",map);
			}
		}
		String key = null;
		if(Util.notNull(group)) {
			key = group;
		}else {
			key = answer;
		}
		
		String url = ProspectUtil.map.get(key);
		if(Util.notNull(url)) {
			resp.sendRedirect(url);
		}else {
			resp.sendRedirect("/sm/open/solutions");
		}
	}

}
