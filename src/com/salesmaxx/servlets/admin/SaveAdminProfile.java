package com.salesmaxx.servlets.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;
import com.google.gson.Gson;
import com.salesmaxx.entities.User;
import com.salesmaxx.entities.exception.InvalidLoginException;
import com.salesmaxx.entities.exception.RollbackFailureException;
import com.salesmaxx.persistence.controllers.UserController;


public class SaveAdminProfile extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6120710121495182650L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String firstName = req.getParameter("first-name");
		String lastName = req.getParameter("last-name");
		
		BlobstoreService bss = BlobstoreServiceFactory
				.getBlobstoreService();
		Map<String, List<BlobKey>> map = bss.getUploads(req);
		List<BlobKey> keys = map.get("picture");
		BlobKey key = null;
		if (keys != null && !keys.isEmpty()) {
			key = keys.get(0);
			Object o = null;
			User user = null;
			HttpSession session = req.getSession();
			synchronized (session) {
				o = session.getAttribute("user");
			}
			if (o == null) {
				throw new InvalidLoginException();
			} else {
				user = (User) o;
				user.setPicture(key);
				user.setFirstName(firstName);
				user.setLastName(lastName);
				synchronized (session) {
					session.setAttribute("user", user);
				}
				UserController cont = new UserController();
				
					cont.edit(user);
					Map<String , String> mp = new HashMap<>();
					mp.put("firstName", firstName);
					mp.put("lastName", lastName);
					mp.put("image", user.getPictureUrl());
					String json = null; 
					json = new Gson().toJson(mp).toString();
					System.out.println(json); 
					resp.getWriter().write(json);
				
			}
		}
	}

}
