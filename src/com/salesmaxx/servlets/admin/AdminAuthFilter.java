package com.salesmaxx.servlets.admin;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.entities.User;
import com.salesmaxx.entities.UserRole;

public class AdminAuthFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) arg0;
		HttpSession session = req.getSession();
		Object o = session.getAttribute("user");
		User user = null;
		if (o != null && o instanceof User) {
			user = (User) o;
			if (user.getRole().equals(UserRole.ADMIN)
					|| user.getRole().equals(UserRole.COURSE_ADMIN)
					|| user.getRole().equals(UserRole.SYSADMIN)) {
				synchronized (session) {
					session.setAttribute("authorized", true);
				}
				
			} else {
				setSessionUnauthorize(session, arg0, arg1);
			}
		} else {
			setSessionUnauthorize(session, arg0, arg1);
			
		}
		arg2.doFilter(arg0, arg1);
	}

	private void setSessionUnauthorize(HttpSession session,
			ServletRequest arg0, ServletResponse arg1) throws IOException,
			ServletException {
		synchronized (session) {
			session.setAttribute("authorized", false);
		}
		HttpServletRequest req = (HttpServletRequest) arg0;
		RequestDispatcher rd = req.getRequestDispatcher("/sm-admin");
		rd.forward(arg0, arg1);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
