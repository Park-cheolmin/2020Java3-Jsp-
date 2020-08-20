package com.min.pjt.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.min.pjt.Const;
import com.min.pjt.ViewResolver;
import com.min.pjt.db.BoardDAO;
import com.min.pjt.vo.BoardVO;


@WebServlet("/board/list")
public class BoardListSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		request.setAttribute("data", BoardDAO.selBoardList());
		ViewResolver.forwardLoginChk("board/list", request, response);
		
		
	}
}

