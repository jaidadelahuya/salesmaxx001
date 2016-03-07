package com.salesmaxx.servlets.admin;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.blobstore.UploadOptions;
import com.salesmaxx.entities.exception.InvalidLoginException;


public class UploadUrlServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		if(session.isNew()) {
			throw new InvalidLoginException();
		} else {
			String callback = req.getParameter("callback");
			BlobstoreService bss = BlobstoreServiceFactory.getBlobstoreService();
			UploadOptions upo = UploadOptions.Builder.withMaxUploadSizeBytesPerBlob(1024*1024*1024).maxUploadSizeBytes(1024*1024*1024);
			String upLoadUrl = bss.createUploadUrl(callback,upo);
			upLoadUrl = resp.encodeURL(upLoadUrl);
			resp.setContentType("text/html");
			req.setAttribute("uploadUrl", upLoadUrl);
			
			resp.getWriter().write(upLoadUrl);
		}
	
		
		
	}
}
