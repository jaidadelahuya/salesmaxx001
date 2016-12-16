package com.salesmaxx.servlets.sm.open;

import java.io.IOException;
import java.util.Random;

import javax.mail.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.util.Util;

public class ForgotPasswordInit extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 452594682874678404L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		int a = Integer.parseInt(Util.generateRandomCode(0, 10));
		int b = Integer.parseInt(Util.generateRandomCode(0, 10));
		
		int c = a + b;
		String op = "";
	
		if(c > 15 && b > a) {
			c = b - a;
			op = b+" - "+a+" = ";
		} else if(c > 12 && a > b) {
			c = a - b;
			op = a +" - "+b+" = ";
		} else if(b !=0 && b%2==0) {
			c = b/2;
			op = b+" / "+2+" = ";
		} else {
			c = a+b;
			op = a+" + "+b+" = ";
		}
		
		HttpSession session = req.getSession();
		synchronized (session) {
			
			session.setAttribute("capthaResult", String.valueOf(c));
			session.setAttribute("captha", op);
			session.removeAttribute("forgotPasswordError");
			session.removeAttribute("forgotPasswordID");
		}
		
		resp.sendRedirect(resp.encodeRedirectURL("/sm/open/forgot/password"));

	}

}
