package com.salesmaxx.servlet;

import java.io.IOException;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.search.Document;
import com.google.appengine.api.search.Field;
import com.salesmaxx.entities.Address;
import com.salesmaxx.entities.WorkShop;
import com.salesmaxx.entities.WorkshopTemplate;
import com.salesmaxx.persistence.controllers.AddressController;
import com.salesmaxx.util.SearchDocumentIndexService;
import com.salesmaxx.util.Util;

public class UpdateIndex extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 711778910049416614L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

			List<WorkshopTemplate> temps = Util.getWorkshopTemplateFromCache();
			List<WorkshopTemplate> wts = null;
			List<Document> docs = new ArrayList<>();
			for (WorkshopTemplate wt : temps) {
				wts = new ArrayList<>();
				wts.add(wt);
				List<WorkShop> ws = Util
						.getAllScheduleWorkshopFromTemplate(wts);
				for (WorkShop w : ws) {

					AddressController ac = new AddressController();
					Address a = ac.findAddress(w.getLocation());
					
					Document d = Util.createDocument(w, a, wt);
					docs.add(d);

					
				}
				
			}

			SearchDocumentIndexService.indexDocument("wk", docs);
			resp.getWriter().write("done");
		}
		

	
}
