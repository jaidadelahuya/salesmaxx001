package com.salesmaxx.servlets.sm.open;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.salesmaxx.beans.ChooseSchedule;
import com.salesmaxx.entities.WorkShop;
import com.salesmaxx.util.Util;

public class WorkshopTemplateToCart extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4728343952164980227L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("id");
		Key key = KeyFactory.createKey(WorkShop.class.getSimpleName(), id);
		List<Key> keys = new ArrayList<>();
		keys.add(key);
		List<WorkShop> list = Util.getScheduledWorkshops(keys);
		//Map<String,String> abuja = Util.getChooseSchedule("abuja",list);
		//Map<String,String> lagos = Util.getChooseSchedule("lagos",list);
		ChooseSchedule cs = new ChooseSchedule();
		//cs.setAbuja(abuja);
		//cs.setLagos(lagos);
		
		//String json = Util.toJsonString(cs);
		resp.setContentType("application/json");
		//resp.getWriter().write(json);
		
		
		
	}

}
