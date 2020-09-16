package com.min.matzip;
//연결시켜주는 역할
import javax.servlet.http.HttpServletRequest;

import com.min.matzip.restaurant.RestaurantController;
import com.min.matzip.user.UserController;

public class HandlerMapper {
	private UserController userCon;
	private RestaurantController restCon;
	
	
	public HandlerMapper() {
		userCon = new UserController();
		restCon = new RestaurantController();
	}
	
	public String nav(HttpServletRequest request) {
		String[] uriArr = request.getRequestURI().split("/");  // .getRequestURI()부분이 "/user/loginProc"로 들어옴
		
		if(uriArr.length < 3) {
			return "405"; //Error
		}
		
		switch(uriArr[1]) {
			case ViewRef.URI_USER:  //user
				switch(uriArr[2]) {
					case "login":
						return userCon.login(request);
					case "loginProc":
						return userCon.loginProc(request);
					case "join":
						return userCon.join(request);
					case "joinProc":
						return userCon.joinProc(request);
					case "ajaxIdChk":
						return userCon.ajaxIdChk(request);
					case "logout":
						return userCon.logout(request);
				}
			case ViewRef.URI_RESTAURANT:  //restaurant
				switch(uriArr[2]) {
					case "restMap":
						return restCon.restMap(request); //메소드호출
					case "restReg":
						return restCon.restReg(request);
					case "restRegProc":
						return restCon.restRegProc(request);
					case "ajaxGetList":
						return restCon.ajaxGetList(request);
					case "restDetail":
						return restCon.restDetail(request);
					case "addMenusProc":
						return restCon.addMenusProc(request);
					case "addRecMenusProc":
						return restCon.addRecMenusProc(request);
					case "ajaxDelRecMenu":
						return restCon.ajaxDelRecMenu(request);

				}
		}
		return "404";	//NotFound
	}
	
}
