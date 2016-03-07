package com.salesmaxx.servlets.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.KeyRange;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.datastore.TransactionOptions;
import com.salesmaxx.entities.Article;
import com.salesmaxx.entities.Video;
import com.salesmaxx.entities.WhitePaper;
import com.salesmaxx.entities.WorkshopTemplate;
import com.salesmaxx.persistence.controllers.WorkshopTemplateController;
import com.salesmaxx.util.Util;

public class SaveWorkshopMaterial extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3324600411862251458L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String name = req.getParameter("mat-name");
		String type = req.getParameter("type");
		String link = req.getParameter("link");
		String workshopId = req.getParameter("workshop");

		boolean ok = Util.notNull(name, type, link, workshopId);

		if (ok) {
			DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
			KeyRange range = null;
			Key key = null;
			if (type.equalsIgnoreCase("ARTICLE")) {
				Article article = new Article();
				range = ds.allocateIds(Article.class.getSimpleName(), 1);
				key = range.getStart();
				article.setId(key.getId());
				article.setLink(link);
				article.setName(name);
				WorkshopTemplate wpt = getTemplate(workshopId);
				if (wpt == null) {
					resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
							"The Workshop ID you entered does not exist.");
				} else {
			
					List<Key> dlbs = wpt.getDownloadables();
					if (dlbs == null) {
						dlbs = new ArrayList<>();
					}
					dlbs.add(key);
					wpt.setDownloadables(dlbs);
					Entity ent1 = Util.WorkshopTemplateToEntity(wpt);
					Entity ent2 = Util.ArticleToEntity(article);
					Transaction txn = ds.beginTransaction(TransactionOptions.Builder.withXG(true));
					ds.put(ent1);
					ds.put(ent2);
					txn.commit();
				}

			} else if (type.equalsIgnoreCase("VIDEO")) {
				Video video = new Video();
				range = ds.allocateIds(Video.class.getSimpleName(), 1);
				key = range.getStart();
				video.setId(key.getId());
				video.setLink(link);
				video.setName(name);
				WorkshopTemplate wpt = getTemplate(workshopId);
				if (wpt == null) {
					resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
							"The Workshop ID you entered does not exist.");
				} else {
					List<Key> dlbs = wpt.getDownloadables();
					if (dlbs == null) {
						dlbs = new ArrayList<>();
					}
					dlbs.add(key);
					wpt.setDownloadables(dlbs);
					Entity ent1 = Util.WorkshopTemplateToEntity(wpt);
					Entity ent2 = Util.VideoToEntity(video);
					Transaction txn = ds.beginTransaction(TransactionOptions.Builder.withXG(true));
					ds.put(ent1);
					ds.put(ent2);
					txn.commit();
				}

			} else if (type.equalsIgnoreCase("WHITE PAPER")) {
				WhitePaper whitePaper = new WhitePaper();
				range = ds.allocateIds(WhitePaper.class.getSimpleName(), 1);
				key = range.getStart();
				whitePaper.setId(key.getId());
				whitePaper.setLink(link);
				whitePaper.setName(name);
				WorkshopTemplate wpt = getTemplate(workshopId);
				if (wpt == null) {
					resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
							"The Workshop ID you entered does not exist.");
				} else {
					List<Key> dlbs = wpt.getDownloadables();
					if (dlbs == null) {
						dlbs = new ArrayList<>();
					}
					dlbs.add(key);
					wpt.setDownloadables(dlbs);
					Entity ent1 = Util.WorkshopTemplateToEntity(wpt);
					Entity ent2 = Util.WhitePaperToEntity(whitePaper);
					Transaction txn = ds.beginTransaction(TransactionOptions.Builder.withXG(true));
					ds.put(ent1);
					ds.put(ent2);
					txn.commit();
				}

			}
		} else {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
					"Fill out the form completely.");
		}

	}

	private WorkshopTemplate getTemplate(String workshopId) {
		WorkshopTemplateController cont = new WorkshopTemplateController();
		Key key = KeyFactory.createKey(WorkshopTemplate.class.getSimpleName(),
				workshopId);
		WorkshopTemplate wkt = cont.findWorkshopTemplate(key);
		return wkt;
	}

}
