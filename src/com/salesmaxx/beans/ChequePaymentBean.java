package com.salesmaxx.beans;

import java.io.Serializable;
import java.util.List;

public class ChequePaymentBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 847849267192674529L;
	private String category;
	private String cursor, cCursor,oCursor;
	private List<ManualPaymentBean> mpbs, cmpbs,ompbs;
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCursor() {
		return cursor;
	}
	public void setCursor(String cursor) {
		this.cursor = cursor;
	}
	public String getcCursor() {
		return cCursor;
	}
	public void setcCursor(String cCursor) {
		this.cCursor = cCursor;
	}
	public String getoCursor() {
		return oCursor;
	}
	public void setoCursor(String oCursor) {
		this.oCursor = oCursor;
	}
	public List<ManualPaymentBean> getMpbs() {
		return mpbs;
	}
	public void setMpbs(List<ManualPaymentBean> mpbs) {
		this.mpbs = mpbs;
	}
	public List<ManualPaymentBean> getCmpbs() {
		return cmpbs;
	}
	public void setCmpbs(List<ManualPaymentBean> cmpbs) {
		this.cmpbs = cmpbs;
	}
	public List<ManualPaymentBean> getOmpbs() {
		return ompbs;
	}
	public void setOmpbs(List<ManualPaymentBean> ompbs) {
		this.ompbs = ompbs;
	}
	@Override
	public String toString() {
		return "ChequePaymentBean [category=" + category + ", cursor=" + cursor
				+ ", cCursor=" + cCursor + ", oCursor=" + oCursor + ", mpbs="
				+ mpbs + ", cmpbs=" + cmpbs + ", ompbs=" + ompbs + "]";
	}
	
	

	
		

}
