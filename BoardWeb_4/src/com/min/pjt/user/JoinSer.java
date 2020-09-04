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


@WebServlet("/join")
public class JoinSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { //화면띄울때
		ViewResolver.forward("user/join", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//업무처리할때
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		String encrypt_pw = MyUtils.encryptString(user_pw);
		String nm = request.getParameter("nm");
		String email = request.getParameter("email");
		
		UserVO param = new UserVO();
		param.setUser_id(user_id);
		param.setUser_pw(encrypt_pw);
		param.setNm(nm);
		param.setEmail(email);
		
		
		int result = UserDAO.insUser(param);
		System.out.println("result : "+ result);
		
		if(result != 1) {
			request.setAttribute("msg", "에러가 발생하였습니다. 관리자에게 문의하십시오.");
			request.setAttribute("data", param);
			
			doGet(request, response);
			return;
		}
		response.sendRedirect("/login");
		
	}

}
