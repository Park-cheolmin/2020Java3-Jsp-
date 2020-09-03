package com.min.pjt.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.min.pjt.MyUtils;
import com.min.pjt.ViewResolver;
import com.min.pjt.db.UserDAO;
import com.min.pjt.vo.UserVO;


@WebServlet("/changePw")
public class ChangePwSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ViewResolver.forward("user/changePw", request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserVO loginUser = MyUtils.getLoginUser(request);
		String type = request.getParameter("type");
		String pw = request.getParameter("pw");
		String encrypt_pw =MyUtils.encryptString(pw); 
		
		UserVO param=new UserVO();
		
		param.setUser_id(loginUser.getUser_id());
		param.setUser_pw(encrypt_pw);		
		
		switch(type) {
		case "1"://현재 비밀번호 확인
			int result = UserDAO.login(param); //비밀번호가 맞으면 1값 아니면 다른값넣기
			
			if(result == 1) {
				request.setAttribute("isAuth", true);
			} else {
				request.setAttribute("msg", "비밀번호를 확인해 주세요.");
			}
			doGet(request, response);
			break;
		case "2":
			param.setI_user(loginUser.getI_user());
			UserDAO.updUser(param);
			response.sendRedirect("/profile?proc=1");
			break;
			
		}
		
			
	}

}
