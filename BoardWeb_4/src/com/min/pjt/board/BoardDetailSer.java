package com.min.pjt.board;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.min.pjt.MyUtils;
import com.min.pjt.ViewResolver;
import com.min.pjt.db.BoardCmtDAO;
import com.min.pjt.db.BoardDAO;
import com.min.pjt.vo.BoardVO;
import com.min.pjt.vo.UserVO;							//pageContext request session application 내장객체


@WebServlet("/board/detail")
public class BoardDetailSer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserVO loginUser = MyUtils.getLoginUser(request);
		
		if(loginUser == null) {
			response.sendRedirect("/login");
			return;
		}
		
		String strI_board = request.getParameter("i_board");
		int i_board = Integer.parseInt(strI_board);
		
		
		//단독으로 조회수 올리기 방지[start!]
		ServletContext application = getServletContext(); //어플리케이션 내장객체 얻어오기
		Integer readI_user = (Integer)application.getAttribute("read_" + strI_board);
		
		if(readI_user == null || readI_user != loginUser.getI_user()) {
			BoardDAO.addHits(i_board);
			application.setAttribute("read_" + strI_board, loginUser.getI_user());
		}
		//단독으로 조회수 올리기 방지![end]
		
		BoardVO param = new BoardVO();
		param.setI_user(loginUser.getI_user());
		param.setI_board(i_board);
		request.setAttribute("data", BoardDAO.selBoard(param));
		
		request.setAttribute("cmtList", BoardCmtDAO.selCmtList(i_board));  
		
		ViewResolver.forward("board/detail", request, response);
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
