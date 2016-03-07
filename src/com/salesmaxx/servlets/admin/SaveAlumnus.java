package com.salesmaxx.servlets.admin;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.entities.Alumnus;
import com.salesmaxx.persistence.controllers.AlumnusController;
import com.salesmaxx.util.Util;

public class SaveAlumnus extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8185095999292881378L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		synchronized (session) {
			session.setAttribute("alumnusSaved",false);
		}
		String title= req.getParameter("title");
		String firstName = req.getParameter("first-name");
		String lastName = req.getParameter("last-name");
		String email = req.getParameter("email");
		String altEmail = req.getParameter("alt-email");
		String phone1 = req.getParameter("phone1");
		String phone2 = req.getParameter("phone2");
		String company = req.getParameter("company");
		String programsAttended = req.getParameter("programs-attended");
		String birthDate = req.getParameter("birthdate");
		
		Alumnus a = new Alumnus();
		a.setTitle(title);
		a.setFirstName(firstName);
		a.setLastName(lastName);
		a.setEmail(email);
		a.setAltEmail(altEmail);
		a.setPhone1(phone1);
		a.setPhone2(phone2);
		a.setCompany(company);
		String[] pa = programsAttended.split(";");
		a.setProgramsAttended(Arrays.asList(pa));
		a.setBirthDate(Util.WebtoDate(birthDate));
		
		AlumnusController ac = new AlumnusController();
		ac.create(a);
	
		
		synchronized (session) {
			session.setAttribute("alumnusSaved", true);
		}
		resp.sendRedirect(resp.encodeRedirectURL("/sm-admin/add-alumnus"));
		
	}

}
