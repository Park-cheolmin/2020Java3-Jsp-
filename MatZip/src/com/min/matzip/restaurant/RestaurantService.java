package com.min.matzip.restaurant;

import java.util.List;

import com.google.gson.Gson;

public class RestaurantService {
	
	private RestaurantDAO dao;
	
	public RestaurantService() {
		dao = new RestaurantDAO();
	}
	
	public int resReg(RestaurantVO param) {
		return dao.insRestaurant(param);
	}
	
	public String getRestList() {
		List<RestaurantDomain> list = dao.selRestList();
		
		Gson gson = new Gson();
		return gson.toJson(list);
	}
}
