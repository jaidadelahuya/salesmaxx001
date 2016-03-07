package com.salesmaxx.servlets.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.salesmaxx.entities.Industry;
import com.salesmaxx.persistence.controllers.IndustryController;

public class AddIndustry extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5946774029583714444L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String val = req.getParameter("name");
		Industry ind = new Industry(val.trim().toUpperCase());
		IndustryController cont = new IndustryController();
		cont.create(ind);
	}
}
