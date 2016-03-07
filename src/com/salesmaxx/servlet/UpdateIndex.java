package com.salesmaxx.servlet;

import java.io.IOException;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.search.Document;
import com.google.appengine.api.search.Field;
import com.salesmaxx.beans.SearchDocument;
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
				Document.Builder db = Document.newBuilder();
				db = db.setId(wt.getWebSafeKey());
				db = db.addField(Field.newBuilder().setName("description")
						.setText(wt.getShortDescription().getValue()));
				db = db.addField(Field.newBuilder().setName("experience")
						.setText(wt.getSkillLevel().get(0)));
				db = db.addField(Field.newBuilder().setName("type")
						.setText("workshop"));
				db = db.addField(Field.newBuilder().setName("workshopCode")
						.setAtom(wt.getWorkshopId().getName()));
				db = db.addField(Field.newBuilder().setName("name")
						.setText(wt.getWorkshopName()));
				for (String s : wt.getIndustries()) {
					db = db.addField(Field.newBuilder().setName("industry")
							.setText(s));
				}

				for (String s : wt.getProfessions()) {
					db = db.addField(Field.newBuilder().setName("profession")
							.setText(s));
				}

				wts = new ArrayList<>();
				wts.add(wt);
				List<WorkShop> ws = Util
						.getAllScheduleWorkshopFromTemplate(wts);
				for (WorkShop w : ws) {

					AddressController ac = new AddressController();
					Address a = ac.findAddress(w.getLocation());
					db = db.addField(Field.newBuilder().setName("location")
							.setText(a.getState()));

					Calendar c = new GregorianCalendar();
					c.setTime(w.getStartDate());
					DateFormatSymbols dfs = new DateFormatSymbols();
					String[] mons = dfs.getMonths();
					db.addField(Field.newBuilder().setName("month")
							.setText(mons[c.get(Calendar.MONTH)]));
				}
				db = db.setRank(Integer.MAX_VALUE);
				docs.add(db.build());
			}

			SearchDocumentIndexService.indexDocument("SearchDocuments", docs);
		}

	
}
