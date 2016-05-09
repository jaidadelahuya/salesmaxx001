package com.salesmaxx.beans;

import java.io.Serializable;
import java.util.List;

public class AdminWorkshopPageBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7777596451311514475L;
	private String module,sort;
	private List<WorkshopTemplateBean> workshopTemplate;
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	
	public List<WorkshopTemplateBean> getWorkshopTemplate() {
		return workshopTemplate;
	}
	public void setWorkshopTemplate(List<WorkshopTemplateBean> workshopTemplate) {
		this.workshopTemplate = workshopTemplate;
	}
	@Override
	public String toString() {
		return "AdminWorkshopPageBean [module=" + module + ", sort=" + sort
				+ ", workshopTemplate=" + workshopTemplate + "]";
	}
	
	

}
