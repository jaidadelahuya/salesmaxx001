package com.salesmaxx.servlets.admin;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.salesmaxx.beans.ScheduleWorkshopDisplay;
import com.salesmaxx.entities.CanceledWorkshop;
import com.salesmaxx.entities.WorkshopTemplate;
import com.salesmaxx.persistence.controllers.CancelWorkshopController;
import com.salesmaxx.util.Util;

public class FindCanceledWorkshops extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3968197974409229925L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String date = req.getParameter("workshop-date");
		String name = req.getParameter("workshop-name");
		String city = req.getParameter("workshop-city");
		Date d = null;

		List<ScheduleWorkshopDisplay> swds = null;

		
		ScheduleWorkshopDisplay swd = null;
		if (Util.notNull(date) && Util.notNull(city)) {
			try {
				d = new SimpleDateFormat("MM/dd/yy").parse(date);
				for (ScheduleWorkshopDisplay ss : swds) {
					if (ss.getsDate().equals(d)
							&& city.equalsIgnoreCase(ss.getLocation()
									.getState())) {
						swd = ss;
						break;
					}
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (Util.notNull(date)) {
			try {
				d = new SimpleDateFormat("MM/dd/yy").parse(date);
				for (ScheduleWorkshopDisplay ss : swds) {
					if (ss.getsDate().equals(d)) {
						swd = ss;
						break;
					}
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (Util.notNull(city)) {
			for (ScheduleWorkshopDisplay ss : swds) {
				if (city.equalsIgnoreCase(ss.getLocation().getState())) {
					swd = ss;
					break;
				}
			}
		}
		if (swd != null) {
			List<CanceledWorkshop> cws = new CancelWorkshopController()
					.findCancelWorkshopByWorkshopId(swd.getId());
			System.out.println(cws);
		}

	}
	
	private List<ScheduleWorkshopDisplay> findByName(String name) {
		List<ScheduleWorkshopDisplay> swds = null;
		if (Util.notNull(name)) {
			List<WorkshopTemplate> lists = new ArrayList<>();
			List<WorkshopTemplate> temps = Util.getWorkshopTemplateFromCache();
			for (WorkshopTemplate wt : temps) {
				if (wt.getWorkshopName().equalsIgnoreCase(name.trim())) {
					lists.add(wt);
				}
			}
			swds = Util.toScheduleWorkshopDisplay(Util
					.getAllScheduleWorkshopFromTemplate(lists));
		} else {
			swds = Util.toScheduleWorkshopDisplay(Util
					.getAllWorkshopSchedulesFromCache());
		}
		return swds;
	}

}
