package com.salesmaxx.servlets.sm.open;

import java.io.IOException;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.mail.BounceNotification;
import com.google.appengine.api.mail.BounceNotificationParser;

public class BounceHandlerServlet extends HttpServlet {

	private static final Logger log = Logger.getLogger(BounceHandlerServlet.class.getName());
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		 try {
			BounceNotification bounce = BounceNotificationParser.parse(req);
			log.info("Bounced Email "+bounce.getOriginal().getSubject());
		
		       // bounce.getOriginal().getFrom() 
		       // bounce.getOriginal().getTo() 
		       // bounce.getOriginal().getSubject() 
		       // bounce.getOriginal().getText() 
		       // bounce.getNotification().getFrom() 
		       // bounce.getNotification().getTo() 
		       // bounce.getNotification().getSubject() 
		       // bounce.getNotification().getText()
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       
	}
}
