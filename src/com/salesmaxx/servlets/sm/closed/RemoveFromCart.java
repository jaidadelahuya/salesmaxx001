package com.salesmaxx.servlets.sm.closed;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.EmbeddedEntity;
import com.google.appengine.api.datastore.Key;
import com.salesmaxx.entities.Cart;
import com.salesmaxx.entities.User;
import com.salesmaxx.persistence.controllers.CartController;

public class RemoveFromCart extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4207496683629435377L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String id = req.getParameter("id");
		HttpSession session = req.getSession();

		Object o = null;

		synchronized (session) {
			o = session.getAttribute("user");
		}

		if (o != null) {
			User u = (User) o;
			Key ky = u.getCart();
			CartController cont = new CartController();
			Cart c = cont.findCart(ky);
			if (c != null) {
				List<EmbeddedEntity> items = c.getItems();
				for(EmbeddedEntity ee : items) {
					if(((String)ee.getProperty("workshopID")).equals(id)) {
						items.remove(ee);
						break;
					}
				}
				c.setItems(items);
				cont.edit(c);
				synchronized (session) {
					session.setAttribute("cart", c);
				}
			}

		} else {
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}

}
