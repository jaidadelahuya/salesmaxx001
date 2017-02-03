package com.salesmaxx.util;

import java.util.HashMap;
import java.util.Map;

public class ProspectUtil {
	
	public static  Map<String,String> map = null;
	
	static {
		map = new HashMap<String, String>();
		map.put("Sales Training", "/sm/open/solutions/workshop/1");
		map.put("experience", "/sm/open/solutions/workshop/2");
		map.put("industry", "/sm/open/solutions/workshop/3");
		map.put("Sales Coaching", "/sm/open/solutions/coaching/1");
		map.put("Sales Tools", "/tools");
		map.put("Interview", "/sm/open/solutions/coaching/2");
		map.put("Sales Performance", "/sm/open/solutions/coaching/3");
		map.put("Sales Management", "/sm/open/solutions/coaching/4");
		map.put("Preparatory", "/sm/open/solutions/coaching/6");
		map.put("Post Interview", "/sm/open/solutions/coaching/8");
		map.put("This is my", "/sm/open/solutions/coaching/5");
		map.put("Required to do", "/sm/open/solutions/coaching/7");
		map.put("yes", "/sm/open/solutions/coaching/5");
		map.put("no", "/");
		
	}

}
