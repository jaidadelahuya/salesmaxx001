package com.salesmaxx.listeners;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.salesmaxx.entities.Cart;
import com.salesmaxx.entities.User;
import com.salesmaxx.persistence.controllers.UserController;

public class SalesMaxxSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent e) {
		HttpSession session = e.getSession();
		
		synchronized (session) {
			Object o = session.getAttribute("user");
			UserController con = new UserController();
			if(o != null) {
				Object o1 = session.getAttribute("cart");
				User u = (User) o;
				if (o1 != null) {
					Cart c = (Cart) o1;
					
					if(u.getCart() != null && u.getCart().equals(c.getCartKey())) {
						
						con.edit(u, c);
					} else {
						con.edit(u);
					}
				} else {
					con.edit(u);
				}
				
				
			}
		}

	}

}
