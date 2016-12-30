package com.salesmaxx.servlets.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.EmbeddedEntity;
import com.google.appengine.api.datastore.Key;
import com.salesmaxx.beans.ChequeInvoice.InvoiceStatus;
import com.salesmaxx.entities.ManualTransaction;
import com.salesmaxx.entities.WorkShop;
import com.salesmaxx.persistence.controllers.ManualTransactionController;
import com.salesmaxx.persistence.controllers.WorkshopController;
import com.salesmaxx.util.Util;

public class AddToWorkshop extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8369310915295429931L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("id");

		HttpSession session = req.getSession();

		Object o = null;

		synchronized (session) {
			o = session.getAttribute("rescheduleOptions");
		}

		if (o != null) {
			RescheduleOption ro = (RescheduleOption) o;
			String txnRef = ro.getTxnRef();
			ManualTransactionController cont = new ManualTransactionController();
			List<ManualTransaction> mt = cont.findByTxnRef(txnRef,
					InvoiceStatus.PENDING);
			if (mt != null && mt.size() == 1) {
				WorkShop w = Util.getWorkshopSchedule(id);
				
				ManualTransaction m = mt.get(0);// we are expecting just one
				try {
					int noD = Integer.parseInt(ro.getQty());
					for (int i = 0; i < noD; i++) {
						List<Key> studs = w.getStudents();
						if(studs == null) {
							studs = new ArrayList<>();
						}
						studs.add(m.getOwnerKey());
						w.setStudents(studs);
						w.setNoEnrolled(w.getNoEnrolled() + 1);
					}

					List<EmbeddedEntity> l = m.getItems();
					if (l.size() == 1) {
						m.setStatus(InvoiceStatus.CLEARED.name());
						cont.create(m);
						synchronized (session) {
							session.setAttribute("newManualTransaction", m);
						}

						resp.sendRedirect(resp
								.encodeRedirectURL("/sm-admin/transaction/manual/reschedule/complete"));
					} else {
						Iterator<EmbeddedEntity> it = l.iterator();
						ManualTransaction nmt = new ManualTransaction(m);
						String oid = ro.getOid();
						while (it.hasNext()) {
							EmbeddedEntity ee = it.next();
							if (oid.equalsIgnoreCase((String) ee
									.getProperty("workshopID"))) {
								ee.setProperty("newWorkshopID", w.getId()
										.getId());
								List<EmbeddedEntity> le = new ArrayList<>();
								le.add(ee);
								nmt.setItems(le);
								nmt.setStatus(InvoiceStatus.CLEARED.name());
								mt.add(nmt);
								it.remove();
								break;
							}
						}
						cont.create(mt);
						List<WorkShop> list = new ArrayList<>();
						list.add(w);
						
						new WorkshopController().edit(list);

						synchronized (session) {
							session.setAttribute("newManualTransaction", nmt);
						}

						resp.sendRedirect(resp
								.encodeRedirectURL("/sm-admin/transaction/manual/reschedule/complete"));
					}

				} catch (NumberFormatException nfe) {

				}
			}

		}
	}

}
