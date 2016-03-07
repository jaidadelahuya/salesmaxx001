package com.salesmaxx.servlets.sm.closed;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.Key;
import com.salesmaxx.beans.MyWorkshopsBean;
import com.salesmaxx.beans.ProfileBean;
import com.salesmaxx.beans.PurchaseHistoryBean;
import com.salesmaxx.beans.SalesMaxxCreditHistory;
import com.salesmaxx.beans.WorkshopDisplay;
import com.salesmaxx.entities.PurchaseHistory;
import com.salesmaxx.entities.User;
import com.salesmaxx.entities.UserGeneralInfo;
import com.salesmaxx.entities.WorkshopTemplate;
import com.salesmaxx.persistence.controllers.PurchaseHistoryController;
import com.salesmaxx.persistence.controllers.UserGeneralInfoController;
import com.salesmaxx.util.Util;

public class InitCurrentUserProfile extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7877573224557633371L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session  = req.getSession();
		
		Object o = null;
		
		synchronized (session) {
			o = session.getAttribute("user");
		}
		
		if(o != null) {
			User user = (User) o;
			Long id = user.getGeneralInfoId();
			
			UserGeneralInfoController cont = new UserGeneralInfoController();
			UserGeneralInfo ugi = cont.findUserGeneralInfo(user,id);
			if(ugi == null) {
				ugi = new UserGeneralInfo();
			}
			
			
			
			String path = req.getRequestURI();
			if(path.contains("/user-profile")) {
				ProfileBean pb = Util.createProfileBean(user,ugi);
				
				synchronized (session) {
					session.setAttribute("profileBean", pb);
					session.setAttribute("ugi",ugi);
				}
				RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/sm/closed/user-profile.jsp");
				rd.include(req, resp);
			} else if(path.contains("/edit-user-profile")) {
				ProfileBean pb = Util.createProfileBean(user,ugi);
				
				synchronized (session) {
					session.setAttribute("profileBean", pb);
					session.setAttribute("ugi",ugi);
				}
				RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/sm/closed/edit-user-profile.jsp");
				rd.include(req, resp);
			} else if(path.contains("/my-workshops")) {
				MyWorkshopsBean mwb = ClosedUtil.createWorkshopBean(ugi.getEnrolledWorkshops(), ugi.getCompletedWorkshops(), user.getCart());
				List<WorkshopTemplate> bootcamps = Util.getWorkshopByExperience("foundation");
				synchronized (session) {
					session.setAttribute("myWorkshopsBean",mwb);
					session.setAttribute("ugi",ugi);
					session.setAttribute("bootcamps",bootcamps);
				}
				RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/sm/closed/my-workshops.jsp");
				rd.include(req, resp);
			}else if(path.contains("/purchase-history")) {
				List<Key> phKeys = ugi.getPurchaseHistory();
				List<PurchaseHistory> phs  = null;
				if(phKeys != null) {
					phs = new PurchaseHistoryController().find(phKeys);
				}
				List<PurchaseHistoryBean> phbs = Util.getPurchaseHistoryBeans(phs);
				synchronized (session) {
					session.setAttribute("purchaseHistorys",phbs);
					session.setAttribute("ugi",ugi);
				}
				RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/sm/closed/purchase-history.jsp");
				rd.include(req, resp);
			} else if(path.contains("/my-certificates")) {
				List<WorkshopTemplate> bootcamps = Util.getWorkshopByExperience("foundation");
				synchronized (session) {
					session.setAttribute("ugi",ugi);
					session.setAttribute("bootcamps",bootcamps);
				}
				RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/sm/closed/certificates.jsp");
				rd.include(req, resp);
			}else if(path.contains("/salesmaxx-credit")) {
				List<WorkshopTemplate> bootcamps = Util.getWorkshopByExperience("foundation");
				SalesMaxxCreditHistory smch = new SalesMaxxCreditHistory();
				smch.setTotal(user.getSalesmaxxCredit());
				smch.setPending(Util.getPendingSalesMaxxCredits(ugi.getEnrolledWorkshops()));
				synchronized (session) {
					session.setAttribute("ugi",ugi);
					session.setAttribute("bootcamps",bootcamps);
					session.setAttribute("smch", smch);
				}
				RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/sm/closed/salesmaxx-credits.jsp");
				rd.include(req, resp);
			}
			
		}
	}

}
