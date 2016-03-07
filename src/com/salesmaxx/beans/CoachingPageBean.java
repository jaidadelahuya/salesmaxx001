package com.salesmaxx.beans;

import java.io.Serializable;
import java.util.List;

import com.salesmaxx.entities.Discussion;
import com.salesmaxx.entities.Testimonial;

public class CoachingPageBean implements Serializable {

	private List<SingleDiscussionPageBean> dis;
	private List<Testimonial> t1;
	private List<FeaturedCoach> fc;
	@Override
	public String toString() {
		return "CoachingPageBean [dis=" + dis + ", t1=" + t1 + ", fc=" + fc
				+ "]";
	}
	public List<SingleDiscussionPageBean> getDis() {
		return dis;
	}
	public void setDis(List<SingleDiscussionPageBean> dis) {
		this.dis = dis;
	}
	public List<Testimonial> getT1() {
		return t1;
	}
	public void setT1(List<Testimonial> t1) {
		this.t1 = t1;
	}
	public List<FeaturedCoach> getFc() {
		return fc;
	}
	public void setFc(List<FeaturedCoach> fc) {
		this.fc = fc;
	}
	
	
}
