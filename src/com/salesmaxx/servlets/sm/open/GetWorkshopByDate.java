package com.salesmaxx.servlets.sm.open;

import java.io.IOException;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.beans.ScheduleWorkshopDisplay;
import com.salesmaxx.entities.WorkShop;
import com.salesmaxx.util.Util;

public class GetWorkshopByDate extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7391288782598524162L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String month = req.getParameter("month");
		String year = req.getParameter("year");
		String criteria = req.getParameter("criteria");

		switch (criteria) {
		case "YEAR":
			Util.yearOnly(year, req, resp);
		case "BOTH":
			Util.both(month, year, req, resp);
		}

	}

}
