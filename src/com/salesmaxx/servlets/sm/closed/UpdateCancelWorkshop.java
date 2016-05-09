package com.salesmaxx.servlets.sm.closed;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;
import com.google.appengine.api.datastore.Transaction;
import com.salesmaxx.entities.CanceledWorkshop;
import com.salesmaxx.entities.PurchaseableItem;
import com.salesmaxx.entities.User;
import com.salesmaxx.entities.UserGeneralInfo;
import com.salesmaxx.entities.WorkShop;
import com.salesmaxx.entities.WorkshopTemplate;
import com.salesmaxx.persistence.controllers.EMF;
import com.salesmaxx.persistence.controllers.PurchaseableItemController;
import com.salesmaxx.persistence.controllers.UserGeneralInfoController;
import com.salesmaxx.persistence.controllers.WorkshopController;
import com.salesmaxx.persistence.controllers.WorkshopTemplateController;
import com.salesmaxx.util.Util;

public class UpdateCancelWorkshop extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9086654839361986480L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String seats = req.getParameter("seats");
		String reason = req.getParameter("reason");
		Object o = null;
		Object o2 = null;
		HttpSession session = req.getSession();
		synchronized (session) {
			o = session.getAttribute("cancelWorkshop");
			o2 = session.getAttribute("user");
		}
		if (o != null && o2 != null) {
			CanceledWorkshop cw = (CanceledWorkshop) o;
			cw.setNoOfDelegates(Long.parseLong(seats));
			if (Util.notNull(reason)) {
				cw.setReason(new Text(reason));
			}
			Object o1 = null;
			synchronized (session) {
				o1 = session.getAttribute("ugi");
			}
			if (o1 != null) {
				UserGeneralInfo ugi = (UserGeneralInfo) o1;
				User u = (User) o2;
				List<Key> ew = ugi.getEnrolledWorkshops();
				List<PurchaseableItem> pis = new PurchaseableItemController()
						.findAll(ew);
				for (PurchaseableItem pi : pis) {
					if (pi.getItemKey().getId() == Long.parseLong(cw
							.getWorkshopId())) {
						if (pi.getQty() == cw.getNoOfDelegates()) {
							ew.remove(pi.getId());
							ugi.setEnrolledWorkshops(ew);
							new UserGeneralInfoController().edit(ugi,
									u.getRegId());
						} else if (pi.getQty() > cw.getNoOfDelegates()) {
							pis.remove(pi);
							pi.setQty(pi.getQty() - cw.getNoOfDelegates());
							pis.add(pi);
							List<Entity> ents = new ArrayList<>();
							for (PurchaseableItem ppi : pis) {
								ents.add(Util.purchaseableItemToEntity(ppi));
							}
							new PurchaseableItemController().create(ents);

						}

					}
				}

				Transaction t = EMF.getDs().beginTransaction();
				EMF.getDs().put(Util.canceledWorkshopToEntity(cw));
				t.commitAsync();

				String msg1 = "<body><div style='width: 40%; margin: 0 auto'>"
						+ "<img alt='SalesMaxx' src='http://www.salesmaxx.com/images/salesmaxx-logo.jpg'/>"
						+ "</div><div><h4 style='padding-bottom: 3%;'>Hello "
						+ u.getFirstName()
						+ ",</h4>"
						+ "<h3 style='color:#d9534f'>Workshop Cancelation</h3>"
						+ "<p>You have canceled your schedule for this workshop</p>"
						+ "<p><strong>Workshop Name: </strong>"
						+ cw.getWorkshopName() + "</p>"
						+ "<p><strong>Workshop Date: </strong>"
						+ cw.getWorkshopDate() + "</p>"
						+ "<p><strong>Workshop Location: </strong>"
						+ cw.getWorkshopLocation() + "</p>"
						+ "<p><strong>Number of Delegates: </strong>"
						+ cw.getNoOfDelegates() + "</p>" + "<p>Regards,</p>"
						+ "<p>SalesMaxx Admin</p></body>";
				String msg2 = "<body><div style='width: 40%; margin: 0 auto'>"
						+ "<img alt='SalesMaxx' src='http://www.salesmaxx.com/images/salesmaxx-logo.jpg'/>"
						+ "</div><div><h4 style='padding-bottom: 3%;'>Hello Admin"
						+ ",</h4>"
						+ "<h3 style='color:#d9534f'>Workshop Cancelation</h3>"
						+ "<p>"
						+ u.getFirstName()
						+ " "
						+ u.getLastName()
						+ " has canceled a workshop schedule</p>See details below<p></p>"
						+ "<p><strong>Workshop Name: </strong>"
						+ cw.getWorkshopName() + "</p>"
						+ "<p><strong>Workshop Date: </strong>"
						+ cw.getWorkshopDate() + "</p>"
						+ "<p><strong>Workshop Location: </strong>"
						+ cw.getWorkshopLocation() + "</p>"
						+ "<p><strong>Number of Delegates: </strong>"
						+ cw.getNoOfDelegates() + "</p>" + "<p>Regards,</p>"
						+ "<p>SalesMaxx Admin</p></body>";

				String email = u.getUsername();
				if (email == null
						&& (u.getEmails() != null && !u.getEmails().isEmpty())) {
					for (String s : u.getEmails()) {
						email = s;
						break;
					}
				}
				if (Util.notNull(email)) {
					try {
						Util.sendEmail(Util.SERVICE_ACCOUNT, email,
								"Workhop Cancelation", msg1);
						Util.sendEmail(Util.SERVICE_ACCOUNT,
								"finance@salesmaxx.com",
								"Workshop Cancellation", msg2);
					} catch (AddressException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				WorkshopController c1 = new WorkshopController();
				WorkShop w = Util.getWorkshopSchedule(cw.getWorkshopId());
				WorkshopTemplate wt = Util
						.getWorkshopTemplateFromScheduleId(
								Util.getWorkshopTemplateFromCache(),
								cw.getWorkshopId());

				for (int i = 0; i < Long.parseLong(seats); i++) {
					w.getStudents().remove(u.getRegId());
				}
				w.setNoEnrolled(w.getNoEnrolled() - cw.getNoOfDelegates());
				wt.setNoEnrolled(wt.getNoEnrolled() - cw.getNoOfDelegates());

				new WorkshopTemplateController().create(wt);
				List<WorkShop> ws = new ArrayList<>();
				ws.add(w);
				new WorkshopController().edit(ws);

			}
		}
		resp.sendRedirect(resp.encodeRedirectURL("/sm/closed/cancelation-info"));

	}

}
