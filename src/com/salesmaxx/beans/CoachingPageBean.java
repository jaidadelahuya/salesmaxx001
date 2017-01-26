package com.salesmaxx.beans;

import java.io.Serializable;
import java.util.List;

import com.salesmaxx.entities.Discussion;
import com.salesmaxx.entities.Testimonial;

public class CoachingPageBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7266517453402081817L;
	private List<SingleDiscussionPageBean> dis,trending;
	@Override
	public String toString() {
		return "CoachingPageBean [dis=" + dis + ", trending=" + trending + "]";
	}
	public List<SingleDiscussionPageBean> getDis() {
		return dis;
	}
	public void setDis(List<SingleDiscussionPageBean> dis) {
		this.dis = dis;
	}
	public List<SingleDiscussionPageBean> getTrending() {
		return trending;
	}
	public void setTrending(List<SingleDiscussionPageBean> trending) {
		this.trending = trending;
	}
	
	
	
	
	
	
}
