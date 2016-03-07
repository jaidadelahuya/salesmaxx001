package com.salesmaxx.servlets.admin;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.salesmaxx.entities.Category;
import com.salesmaxx.entities.exception.RollbackFailureException;
import com.salesmaxx.persistence.controllers.CategoryController;
import com.salesmaxx.util.Util;

public class SaveWorkshopCategory extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7590557165273894229L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String categoryName = req.getParameter("category-name");
		String[] workshops = req.getParameterValues("workshops");
		
		boolean ok = Util.notNull(categoryName);
		
		if (ok) {
	
			ok = Util.notNullArray(workshops);
			if(!ok) {
				resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, "You have to select at least one Workshop.");
				return;
			}
			BlobstoreService bss = BlobstoreServiceFactory
					.getBlobstoreService();
			Map<String, List<BlobKey>> map = bss.getUploads(req);
			List<BlobKey> keys = map.get("category-image");
			BlobKey key = null;
			if (keys != null && !keys.isEmpty()) {
				key = keys.get(0);
				Category cat = new Category(categoryName);
				cat.setImage(key);
				cat.setWorkshops(Arrays.asList(workshops));
				
				CategoryController cont = new CategoryController();
				try {
					cont.create(cat);
				} catch (RollbackFailureException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, "You have to select an Image");
			}
		}else {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Fill out the form completely");
		}
	}
}
