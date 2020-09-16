package com.min.matzip.restaurant;

import javax.servlet.http.HttpServletRequest;

import com.min.matzip.CommonDAO;
import com.min.matzip.CommonUtils;
import com.min.matzip.Const;
import com.min.matzip.SecurityUtils;
import com.min.matzip.ViewRef;
import com.min.matzip.vo.RestaurantRecommendMenuVO;
import com.min.matzip.vo.RestaurantVO;
import com.min.matzip.vo.UserVO;

public class RestaurantController {
	private RestaurantService service = new RestaurantService();
	
	public String restMap(HttpServletRequest request) {
		request.setAttribute(Const.TITLE, "메인화면"); 
		request.setAttribute(Const.VIEW, "restaurant/restMap"); 
		return ViewRef.TEMP_MENU_TEMP;
	}
	
	public String restReg(HttpServletRequest request) {
		final int I_M = 1; //카테고리 코드
		request.setAttribute("categoryList", CommonDAO.selCodeList(1));
		
		
		request.setAttribute(Const.TITLE, "가게 등록"); 
		request.setAttribute(Const.VIEW, "restaurant/restReg"); 
		return ViewRef.TEMP_MENU_TEMP;
	}
	
	public String restRegProc(HttpServletRequest request) {
		String nm = request.getParameter("nm");
		String addr = request.getParameter("addr");
		double lat = Double.parseDouble(request.getParameter("lat"));
		double lng = Double.parseDouble(request.getParameter("lng"));
		int cd_category = Integer.parseInt(request.getParameter("cd_category"));
		
		UserVO loginUser = SecurityUtils.getLoginUser(request);
		int i_user = loginUser.getI_user();
		
		RestaurantVO param = new RestaurantVO();
		param.setNm(nm);
		param.setAddr(addr);
		param.setLat(lat);
		param.setLng(lng);
		param.setCd_category(cd_category);
		param.setI_user(i_user);
		

		
		int result = service.resReg(param);
		
		return "redirect:/restaurant/restReg";
	}
	
	public String ajaxGetList(HttpServletRequest request) {
		return "ajax: " + service.getRestList();
	}
	
	public String restDetail(HttpServletRequest request) {
		int i_rest = CommonUtils.getIntParameter("i_rest", request);
		
		RestaurantVO param = new RestaurantVO();
		param.setI_rest(i_rest);
		
		request.setAttribute("css", new String[] {"restaurant"});
		request.setAttribute("recommendMenuList", service.getRecommendMenuList(i_rest));
		request.setAttribute("menuList", service.getMenuList(i_rest));
		request.setAttribute("data", service.getRest(param));
		request.setAttribute(Const.TITLE, "디테일"); 
		request.setAttribute(Const.VIEW, "restaurant/restDetail"); 
		return ViewRef.TEMP_MENU_TEMP;
	}
	
	public String addMenusProc(HttpServletRequest request) { //메뉴
		int i_rest = service.addMenus(request);
		return "redirect:/restaurant/restDetail?i_rest=" + i_rest;
	}
	
	public String addRecMenusProc(HttpServletRequest request) { //웹에서 메뉴추가한 정보들이 request에 담겨있음   (   추천메뉴)
		int i_rest = service.addRecMenus(request); //파일만 유일하게 service에게 request를 넘겨줌
		return "redirect:/restaurant/restDetail?i_rest=" + i_rest;
	}
	
	public String ajaxDelRecMenu(HttpServletRequest request) {
		int i_rest = CommonUtils.getIntParameter("i_rest", request);
		int seq = CommonUtils.getIntParameter("seq", request);
		int i_user = SecurityUtils.getLoginUserPk(request);
		
		RestaurantRecommendMenuVO param = new RestaurantRecommendMenuVO();
		param.setI_rest(i_rest);
		param.setSeq(seq);
		param.setI_user(i_user);
		
		int result = service.delRecMenu(param);
		
	
		return "ajax:" + result;
	}
}
