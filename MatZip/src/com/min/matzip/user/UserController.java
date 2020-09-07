package com.min.matzip.user;

import javax.servlet.http.HttpServletRequest;

import com.min.matzip.Const;
import com.min.matzip.ViewRef;

public class UserController {
	
	//     /user/login
	public String login(HttpServletRequest request) { //화면열때 필수
		request.setAttribute(Const.TITLE, "로그인"); //어떤화면을 열지
		request.setAttribute(Const.VIEW, "user/login"); //어떤 템플릿을 열지
		
		return ViewRef.TEMP_DEFAULT;
	}

	public String join(HttpServletRequest request) {
		request.setAttribute(Const.TITLE, "회원가입");
		request.setAttribute(Const.VIEW, "user/join");
		
		return  ViewRef.TEMP_DEFAULT;
	}
	
	public String joinProc(HttpServletRequest request) {
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		String nm = request.getParameter("nm");
		
		return  "redirect:/user/login";
	}
}
