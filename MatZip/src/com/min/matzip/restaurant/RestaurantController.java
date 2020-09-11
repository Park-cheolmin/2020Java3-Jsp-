package com.min.matzip.restaurant;

import javax.servlet.http.HttpServletRequest;

import com.min.matzip.CommonDAO;
import com.min.matzip.Const;
import com.min.matzip.SecurityUtils;
import com.min.matzip.ViewRef;
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
		
		UserVO loginUser = SecurityUtils.getloginUser(request);
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
		return "ajax : " + service.getRestList();
	}
}
