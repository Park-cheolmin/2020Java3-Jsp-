package com.min.pjt.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.min.pjt.Const;
import com.min.pjt.MyUtils;
import com.min.pjt.ViewResolver;
import com.min.pjt.db.BoardDAO;
import com.min.pjt.vo.BoardDomain;
import com.min.pjt.vo.BoardVO;
import com.min.pjt.vo.UserVO;


@WebServlet("/board/regmod")
public class BoardRegmodSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	//화면 띄우는 용도(등록창/수정창)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strI_board = request.getParameter("i_board");
		int i_board = MyUtils.parseStrToInt(strI_board);
		
		if(i_board != 0) {//수정
			request.setAttribute("data", BoardDAO.selBoard(i_board));
		}
		
		

		
		ViewResolver.forwardLoginChk("board/regmod", request, response);  // ViewResolver.forward에는  파일명표기 
	}


	// 처리용도 (DB에 등록/수정)실시
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String ctnt = request.getParameter("ctnt");
		String strI_board = request.getParameter("i_board"); //request로받으면 어떤 값이든 string으로 넘어온다
		
	
		UserVO loginUser = MyUtils.getLoginUser(request);
		
		System.out.println("title: " + title);
		System.out.println("ctnt: " + ctnt);
		
		
		BoardVO param= new BoardVO(); //위에 받은 값을 객체에 담는다
		param.setTitle(title);
		param.setCtnt(ctnt);
		param.setI_user(loginUser.getI_user());
		int result=0;
		
		
		if(strI_board == "") { //등록
			result = BoardDAO.insBoard(param);
			response.sendRedirect("/board/list");
		} else {	//수정
			int i_board = MyUtils.parseStrToInt(strI_board);
			param.setI_board(i_board);
			result = BoardDAO.updBoard(param);
			response.sendRedirect("/board/detail?i_board=" + strI_board);
		}
		
		
		
		System.out.println("result : " + result);
		
		
		
		
		
	}

}
