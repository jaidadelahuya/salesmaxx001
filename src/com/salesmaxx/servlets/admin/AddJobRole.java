package com.salesmaxx.servlets.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.salesmaxx.entities.JobRole;
import com.salesmaxx.persistence.controllers.JobRoleController;

public class AddJobRole extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1845681056267096316L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String val = req.getParameter("name");
		JobRole jr = new JobRole(val.trim());
		JobRoleController cont = new JobRoleController();
		cont.create(jr);
	}
}
