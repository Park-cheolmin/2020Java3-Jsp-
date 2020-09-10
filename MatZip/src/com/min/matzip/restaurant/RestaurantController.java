package com.min.matzip.restaurant;

import javax.servlet.http.HttpServletRequest;

import com.min.matzip.Const;
import com.min.matzip.ViewRef;

public class RestaurantController {
	public String restMap(HttpServletRequest request) {
		request.setAttribute(Const.TITLE, "메인화면"); 
		request.setAttribute(Const.VIEW, "restaurant/restMap"); 
		return ViewRef.TEMP_MENU_TEMP;
	}
}
