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
import com.min.pjt.vo.BoardVO;
import com.min.pjt.vo.UserVO;


@WebServlet("/board/regmod")
public class BoardRegmodSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	//화면 띄우는 용도(등록창/수정창)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ViewResolver.forwardLoginChk("board/regmod", request, response);  // ViewResolver.forward에는  파일명표기 
	}


	// 처리용도 (DB에 등록/수정)실시
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String ctnt = request.getParameter("ctnt");
		String strI_board = request.getParameter("i_board");
		
		
		BoardVO param= new BoardVO(); //위에 받은 값을 객체에 담는다

		if(strI_board != "") {
			param.setI_board(Integer.parseInt(strI_board));
			param.setTitle(title);
			param.setCtnt(ctnt);
		} else {
			HttpSession hs = request.getSession();
			UserVO loginUser = MyUtils.getLoginUser(request);
			param.setTitle(title);
			param.setCtnt(ctnt);
			param.setI_user(loginUser.getI_user());
			int result = BoardDAO.updBoard(param);
		}
		
		
		response.sendRedirect("/board/list");
		
	}

}
