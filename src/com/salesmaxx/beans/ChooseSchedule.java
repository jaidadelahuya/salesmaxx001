package com.salesmaxx.beans;

import java.io.Serializable;
import java.util.Map;

public class ChooseSchedule implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2224121757634055425L;
	
	Map<String,String> abuja;
	Map<String,String> lagos;
	public Map<String, String> getAbuja() {
		return abuja;
	}
	public void setAbuja(Map<String, String> abuja) {
		this.abuja = abuja;
	}
	public Map<String, String> getLagos() {
		return lagos;
	}
	public void setLagos(Map<String, String> lagos) {
		this.lagos = lagos;
	}
	@Override
	public String toString() {
		return "ChooseSchedule [abuja=" + abuja + ", lagos=" + lagos + "]";
	}
	
	

}
