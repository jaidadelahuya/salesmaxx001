package com.salesmaxx.servlets.sm.open;

public class ProspectPreferenceFactory {

	public static ProspectPreference createProspectPeference(String solution) {
		if(solution!=null) {
			if(solution.equalsIgnoreCase("workshop")) {
				WorkshopPreference wp = new WorkshopPreference();
				wp.init();
				return wp;
			}else if(solution.equalsIgnoreCase("coaching")) {
				CoachingPreference cp = new CoachingPreference();
				cp.init();
				return cp;
			}else if (solution.equalsIgnoreCase("tools")) {
				ToolPreference tp = new ToolPreference();
				tp.init();
				return tp;
			}
		}
		return null;
	}

}
