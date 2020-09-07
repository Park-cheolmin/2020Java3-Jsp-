package com.min.matzip;

import javax.servlet.http.HttpServletRequest;

import com.min.matzip.user.UserController;

public class HandlerMapper {
	private UserController userCon;
	
	public HandlerMapper() {
		userCon = new UserController();
	}
	
	public String nav(HttpServletRequest request) {
		String[] uriArr = request.getRequestURI().split("/");
		
		if(uriArr.length < 3) {
			return "405"; //Error
		}
		
		switch(uriArr[1]) {
		case ViewRef.URI_USER:
			switch(uriArr[2]) {
				case "login":
					return userCon.login(request);
				case "join":
					return userCon.join(request);
				case "joinProc":
					return userCon.joinProc(request);
			}
			
		}
		return "404";	//NotFound
	}
	
}
