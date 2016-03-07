package com.salesmaxx.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cursor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6527261720527392347L;
	
	private List<List<Object>> sublist;
	private int start,end,subListSize;
	@Override
	public String toString() {
		return "Cursor [sublist=" + sublist + ", start=" + start + ", end="
				+ end + "]";
	}
	
	
	public int getSubListSize() {
		return subListSize;
	}


	public void setSubListSize(int subListSize) {
		this.subListSize = subListSize;
	}


	public List<List<Object>> getSublist() {
		return sublist;
	}
	public void setSublist(List<List<Object>> sublist) {
		this.sublist = sublist;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public Cursor(List<Object> list, int subListSize, int start, int end) {
		sublist = new ArrayList<List<Object>>();
		List<Object> sl = null;
		while(list.size() != 0) {
			sl = new ArrayList<>();
			for(int i = 0; i < list.size(); i++) {
				sl.add(list.get(i));
				if(i == subListSize - 1) {
					break;
				}
			}
			sublist.add(sl);
			list.removeAll(sl);
		}
		this.subListSize = subListSize;
		this.start = start;
		this.end = end;
	}
	
	
}
