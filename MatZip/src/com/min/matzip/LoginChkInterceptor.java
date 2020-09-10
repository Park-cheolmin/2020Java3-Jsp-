package com.min.matzip;

import javax.servlet.http.HttpServletRequest;

public class LoginChkInterceptor { //잘못된 경로로 갔을떄 잡아줄려고
	//null이 리턴되면 아무일없음
	//문자열이 리턴되면 그문자열로 sendRedirect함
	public static String routerChk(HttpServletRequest request) {

		String[] chkUserUriArr = {"login", "loginProc", "join", "joinProc", "ajaxIdChk"}; //2차주소값 

		boolean isLogout = SecurityUtils.isLogout(request);
		String[] targetUri = request.getRequestURI().split("/");		
		if(targetUri.length < 3) { return null; }		
		if(isLogout) { //로그아웃 상태			
			if(targetUri[1].equals(ViewRef.URI_USER)) {
				for(String uri : chkUserUriArr) {
					if(uri.equals(targetUri[2])) {
						return null;
					}
				}
			}
			return "/user/login";			
		} else { //로그인 상태 , 로그인이 되어있을때 chkUserUriArr에있는 곳으로 갈필요가 없기때문에 막는다
			if(targetUri[1].equals(ViewRef.URI_USER)) {
				for(String uri : chkUserUriArr) {
					if(uri.equals(targetUri[2])) {
						return "/restaurant/restMap";
					}
				}
			}
			return null;			
		}		
	}
}
