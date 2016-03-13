package com.salesmaxx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;

@SuppressWarnings("serial")
public class SalesmaxxServlet extends HttpServlet {
	public static final String ACCOUNT_SID = "AC24197fa2d53a9fc93cbddf752fe76772";
	  public static final String AUTH_TOKEN = "4baa6aa022dd8a1986057f7894ed06fe";
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
		 
	    // Build a filter for the MessageList
	    List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("Body", "Jenny please?! I love you <3"));
	    params.add(new BasicNameValuePair("To", "+2347051212230"));
	    params.add(new BasicNameValuePair("From", "+12242315649"));
	 
	    MessageFactory messageFactory = client.getAccount().getMessageFactory();
	    Message message;
		try {
			message = messageFactory.create(params);
		} catch (TwilioRestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   // System.out.println(message.getSid());
	}
}
