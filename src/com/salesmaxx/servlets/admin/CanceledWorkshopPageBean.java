package com.salesmaxx.servlets.admin;

import java.io.Serializable;
import java.util.List;

public class CanceledWorkshopPageBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7572864845022151169L;
	
	private String status, pendingCursor, clearedCursor;
	private List<CancelWorkshopBean> pendingWorkshops, clearedWorkshops;
	@Override
	public String toString() {
		return "CanceledWorkshopPageBean [status=" + status
				+ ", pendingCursor=" + pendingCursor + ", clearedCursor="
				+ clearedCursor + ", pendingWorkshops=" + pendingWorkshops
				+ ", clearedWorkhops=" + clearedWorkshops + "]";
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPendingCursor() {
		return pendingCursor;
	}
	public void setPendingCursor(String pendingCursor) {
		this.pendingCursor = pendingCursor;
	}
	public String getClearedCursor() {
		return clearedCursor;
	}
	public void setClearedCursor(String clearedCursor) {
		this.clearedCursor = clearedCursor;
	}
	public List<CancelWorkshopBean> getPendingWorkshops() {
		return pendingWorkshops;
	}
	public void setPendingWorkshops(List<CancelWorkshopBean> pendingWorkshops) {
		this.pendingWorkshops = pendingWorkshops;
	}
	public List<CancelWorkshopBean> getClearedWorkshops() {
		return clearedWorkshops;
	}
	public void setClearedWorkhops(List<CancelWorkshopBean> clearedWorkhops) {
		this.clearedWorkshops = clearedWorkhops;
	}
	
	
	

}
