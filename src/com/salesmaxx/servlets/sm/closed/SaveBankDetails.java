package com.salesmaxx.servlets.sm.closed;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.salesmaxx.beans.ProfileBean;
import com.salesmaxx.entities.BankAccountDetails;
import com.salesmaxx.entities.User;
import com.salesmaxx.entities.UserGeneralInfo;
import com.salesmaxx.util.Util;

public class SaveBankDetails extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5068070852548692729L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		HttpSession session = req.getSession();
		Object o = null;
		Object o1 = null;
		Object o2 = null;
		
		synchronized (session) {
			o = session.getAttribute("user");
			o1 = session.getAttribute("ugi");
			o2 = session.getAttribute("profileBean");
		}
		
		if(o!=null && o1!=null&&o2!=null) {
			String accName = req.getParameter("acc-name");
			String accNumber = req.getParameter("acc-number");
			String bankName = req.getParameter("bank-name");

			if (!Util.notNull(accName)) {
				resp.sendError(800);
				return;
			}

			if (!Util.notNull(accNumber)) {
				resp.sendError(801);
				return;
			}

			if (!Util.notNull(bankName)) {
				resp.sendError(802);
				return;
			}
			
			BankAccountDetails bads = new BankAccountDetails();
			bads.setAccName(accName);
			bads.setAccNumber(accNumber);
			bads.setBankName(bankName);
			User u = (User) o;
			UserGeneralInfo ugi = (UserGeneralInfo) o1;
			ProfileBean pb = (ProfileBean) o2;
			bads.setUserKey(u.getRegId());
			ugi.setBankDetails(bads.getId());
			pb.setBankAccountDetails(bads);
			
			Entity e = Util.UserGeneralInfoToEntity(ugi, u.getRegId());
			Entity e1 = Util.bankAccountDetailsToEntity(bads);
			
			List<Entity> ents = new ArrayList<>();
			ents.add(e1); ents.add(e);
			
			Util.create(ents);
			
			synchronized (session) {
				session.setAttribute("ugi", ugi);
				session.setAttribute("profileBean", pb);
			}
			
			
		}else {
			//to login page
		}
		
		
		
		
	}

}
