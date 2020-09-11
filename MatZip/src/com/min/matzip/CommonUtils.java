package com.min.matzip;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CommonUtils {
	public static int parseStingToInt(String str) {
		try {
			return Integer.parseInt(str);
		} catch(Exception e) {
			return 0;
		}
	}
	
	public static double parseStringToDouble(String str) {
		try {
			return Double.parseDouble(str);
		} catch(Exception e) {
			return 0;
		}
	}
	
	
}
