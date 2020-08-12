package com.min.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.min.board.common.Utils;
import com.min.board.db.BoardDao;
import com.min.board.vo.BoardVO;

@WebServlet("/BoardDel")
public class BoardDelSer extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String strI_board = request.getParameter("i_board");
		int i_board = Utils.parseStrToInt(strI_board);
		System.out.println("del-i-board : " + i_board);
		
		if(i_board == 0) { //잘못된 값을 보냄(문자열 섞여있음)
			response.sendRedirect("/errPage?err=2&target=BoardList");
			return;
		}
		
		BoardVO param = new BoardVO();
		param.setI_board(i_board);
		
		int result = BoardDao.delBoard(param);
		System.out.println("del-result: " + result);
				
		if(result == 1) {
			response.sendRedirect("/BoardList");
		}else {
			response.sendRedirect("/errPage?err=1&target=BoardList");
		}
	}
}
