package com.min.board.common;

public class Utils {
	public static  int parseStrToInt(String param) {
		return parseStrToInt(param,0);
	}
	
	public static  int parseStrToInt(String param, int n) {
		
		try {
			return Integer.parseInt(param);
		} catch(Exception e) {
			return n;
		}
	}
}
