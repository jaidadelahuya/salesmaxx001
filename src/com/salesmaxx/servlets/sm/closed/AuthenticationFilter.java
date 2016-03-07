package com.salesmaxx.servlets.sm.closed;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.Text;
import com.salesmaxx.beans.CoachingPost;
import com.salesmaxx.entities.Discussion;
import com.salesmaxx.entities.User;

public class AuthenticationFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) arg0;

		HttpSession session = req.getSession();

		Object o = null;

		synchronized (session) {
			o = session.getAttribute("user");
		}

		HttpServletResponse resp = (HttpServletResponse) arg1;

		if (o == null) {
			if (req.getRequestURI().contains("proceed-to-checkout")) {
				session.setAttribute("requestURI", req.getRequestURI());
				resp.sendRedirect("/sm/open/login-page");
				return;
			} else if (req.getRequestURI().contains(
					"sm/closed/submit-discussion")) {
				session.setAttribute("requestURI", req.getRequestURI());
				String title = req.getParameter("title");
				String body = req.getParameter("body");
				String tags = req.getParameter("tags");
				String category = req.getParameter("category");
				String privacy = req.getParameter("privacy");
				String notify = req.getParameter("notify-me");
				CoachingPost cp = new CoachingPost();
				cp.setTitle(title);
				cp.setBody(body);
				cp.setCategory(category);
				cp.setNotify(notify);
				cp.setPrivacy(privacy);
				cp.setTags(tags);
				session.setAttribute("coachingPost", cp);
				resp.sendRedirect("/sm/open/login-page");
			} else if (req.getRequestURI().contains(
					"sm/closed/post-comment")) {
				String body = req.getParameter("comment");
				String webkey = req.getParameter("webkey");
				synchronized (session) {
					session.setAttribute("newComment", body);
					session.setAttribute("newPostWebkey", webkey);
					session.setAttribute("requestURI", req.getRequestURI());
				}
				
				resp.sendRedirect("/sm/open/login-page");
			} else if(req.getRequestURI().contains("/save-testimonial")) {
				String statement = req.getParameter("statement");
				synchronized (session) {
					session.setAttribute("testimonialStatement", statement);
					session.setAttribute("requestURI", req.getRequestURI());
				}
				resp.sendRedirect("/sm/open/login-page");
			} else {
				session.setAttribute("requestURI", req.getRequestURI());
				resp.sendRedirect("/sm/open/login-page");
			}

		} else {
			User u = (User) o;
			if (u.isAuthenticated()) {
				
				if (req.getRequestURI().contains(
						"sm/closed/post-comment")) {
					String body = req.getParameter("comment");
					String webkey = req.getParameter("webkey");
					synchronized (session) {
						session.setAttribute("newComment", body);
						session.setAttribute("newPostWebkey", webkey);
					}
					
				} else if(req.getRequestURI().contains("/save-testimonial")) {
					String statement = req.getParameter("statement");
					synchronized (session) {
						session.setAttribute("testimonialStatement", statement);
					}
				}
				arg2.doFilter(arg0, arg1);
			}
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
