package com.min.pjt.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.min.pjt.ViewResolver;
import com.min.pjt.db.BoardDAO;
import com.min.pjt.vo.BoardVO;


@WebServlet("/board/detail")
public class BoardDetailSer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strI_board = request.getParameter("i_board");
		int i_board = Integer.parseInt(strI_board);
		System.out.println("i_board: " + i_board);
		
		BoardVO param= new BoardVO();
		param.setI_board(i_board);
		
		BoardVO data = BoardDAO.selBoard(param);
		
		request.setAttribute("data", data);
		

	
		ViewResolver.forwardLoginChk("board/detail", request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
