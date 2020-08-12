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
//detail(view용) 과  수정화면(detail의 내용을수정)이 가져오는건 같다

@WebServlet("/BoardMod")
public class BoardMod extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { //display담당
		String strI_board = request.getParameter("i_board");
		int i_board = Utils.parseStrToInt(strI_board); 
		if(i_board == 0) {
			response.sendRedirect("/BoardList");
			return;
		}
		
		BoardVO param = new BoardVO();
		param.setI_board(i_board);
		
		request.setAttribute("data", BoardDao.selBoard(param));
		
		
		String jsp = "/WEB-INF/view/BoardRegmod.jsp";
		request.getRequestDispatcher(jsp).forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { //처리담당
		String strI_board = request.getParameter("i_board");
		String title = request.getParameter("title"); 
		String ctnt = request.getParameter("ctnt");
		
		System.out.println("i_board: " + strI_board);
		System.out.println("title: " + title);
		System.out.println("ctnt: " + ctnt);

		BoardVO param = new BoardVO();
		param.setI_board(Utils.parseStrToInt(strI_board));
		param.setTitle(title);
		param.setCtnt(ctnt);

		
		int result = BoardDao.updBoard(param);
		System.out.println("result : " + result);
		
		
		 if(result == 1) { //정상
	    	  response.sendRedirect("/BoardDetail?i_board="+strI_board);
	         
	      } else {
	    	  request.setAttribute("msg", "값을 잘못입력했습니다.");
	    	  doGet(request, response);
	      }
		
	
		
		
		
		
	
	}

}
