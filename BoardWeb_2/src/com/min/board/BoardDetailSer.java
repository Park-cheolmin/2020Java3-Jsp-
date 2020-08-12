package com.min.board;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.min.board.common.Utils;
import com.min.board.db.BoardDao;
import com.min.board.db.DbCon;
import com.min.board.vo.BoardVO;

@WebServlet("/BoardDetail")
public class BoardDetailSer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { //주로 화면띄우기
		String strI_board = request.getParameter("i_board");
		
		int i_board = Utils.parseStrToInt(strI_board); //"1111"로 받으면 숫자 1111로 바꿔주고 문자가 하나라도 섞여있다면 0을 return
		
		if(i_board == 0) {
			response.sendRedirect("/BoardList");
			return;
		}
		
		BoardVO param = new BoardVO();
		param.setI_board(i_board);
		
		BoardVO data = BoardDao.selBoard(param);
		request.setAttribute("data", data);
		
		String jsp = "/WEB-INF/view/BoardDetail.jsp";
		request.getRequestDispatcher(jsp).forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
