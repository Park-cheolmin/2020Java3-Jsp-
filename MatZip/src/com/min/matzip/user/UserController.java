package com.min.matzip.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.min.matzip.CommonUtils;
import com.min.matzip.Const;
import com.min.matzip.ViewRef;
import com.min.matzip.vo.UserVO;

public class UserController { //넘어온값을 담아서 보내는 역할 까지
	
	private UserService service = new UserService();
	
	//     /user/login
	public String login(HttpServletRequest request) { //화면열때 필수
		String error = request.getParameter("error");
		
		if(error != null) {
			switch(error) {
			case "2":
				request.setAttribute("msg", "아이디를 확인해 주세요.");
				break;
			case "3":
				request.setAttribute("msg", "비밀번호를 확인해 주세요.");
				break;
			}
		}
		
		request.setAttribute(Const.TITLE, "로그인"); //어떤화면을 열지
		request.setAttribute(Const.VIEW, "user/login"); //어떤 템플릿을 열지
		return ViewRef.TEMP_DEFAULT;
	}
	
	public String loginProc(HttpServletRequest request) {
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		
		UserVO param = new UserVO();
		param.setUser_id(user_id);
		param.setUser_pw(user_pw);
		
		int result = service.login(param); 
		
		if(result == 1) { //로그인 성공, 세션에다가 넣어줘야함
			HttpSession hs = request.getSession();
			hs.setAttribute(Const.LOGIN_USER, param);
			
			return "redirect:/restaurant/restMap";
		} else {
			return "redirect:/user/login?user_id=" + user_id + "&error=" + result;
		}
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
		
		UserVO param = new UserVO();
		param.setUser_id(user_id);
		param.setUser_pw(user_pw);
		param.setNm(nm);
		
		int result = service.join(param);
		
		return  "redirect:/user/login";
	}
	
	public String ajaxIdChk(HttpServletRequest request) {
		String user_id = request.getParameter("user_id");
		UserVO param = new UserVO();
		param.setUser_id(user_id);
		param.setUser_pw("");
		
		int result = service.login(param);
		
		return String.format("ajax:{\"result\": %s}", result);
	}
	
	public String logout(HttpServletRequest request) {
		HttpSession hs = request.getSession();
		hs.invalidate();//session에있는걸 모두 지우는기능
		
		return  "redirect:/user/login";
	}
	
}
