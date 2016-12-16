package com.salesmaxx.servlets.sm.open;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.KeyFactory;
import com.google.gson.Gson;
import com.salesmaxx.entities.WorkShop;
import com.salesmaxx.entities.WorkshopTemplate;
import com.salesmaxx.persistence.controllers.WorkshopController;
import com.salesmaxx.util.Util;

public class GetCalendarData extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8402680435278571454L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String start = req.getParameter("start");
		String end = req.getParameter("end");

		if (Util.notNull(start, end)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date startDate = sdf.parse(start);
				Date endDate = sdf.parse(end);

				WorkshopController cont = new WorkshopController();
				List<WorkShop> workshops = cont.getWorkshops(startDate, endDate);
						
				List<Map<String, String>> list = new ArrayList<>();
				for (WorkShop w : workshops) {
					WorkshopTemplate wTemplate = Util
							.getWorkshopTemplateFromScheduleId(
									Util.getWorkshopTemplateFromCache(),
									String.valueOf(w.getId().getId()));
					if(wTemplate==null)continue;
					Map<String, String> map = new HashMap<>();
					map.put("id", KeyFactory.keyToString(w.getId()));
					map.put("title", wTemplate.getWorkshopName());
					Calendar c = new GregorianCalendar();
					c.setTime(w.getStartDate());
					c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), 8, 30);
					Date d = new Date(c.getTimeInMillis());
					String sDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(d).replace(" ", "T");
					Calendar c1 = new GregorianCalendar();
					c1.setTime(w.getStartDate());
					c1.set(c1.get(Calendar.YEAR), c1.get(Calendar.MONTH), c1.get(Calendar.DATE), 16, 30);
					Date d1 = new Date(c1.getTimeInMillis());
					String eDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(d1).replace(" ", "T");
					map.put("start", sDate);
					map.put("end",eDate);
					String url = resp.encodeRedirectURL("/sm/open/workshop-information?id="+wTemplate.getWorkshopId().getName());
					map.put("url", url);
					map.put("color", "white");
					map.put("backgroundColor", "#3b5998");
					map.put("borderColor","blue");
					map.put("textColor", "white");
			
					list.add(map);
				}
				resp.setContentType("application/json");
				resp.getWriter().write(new Gson().toJson(list));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
