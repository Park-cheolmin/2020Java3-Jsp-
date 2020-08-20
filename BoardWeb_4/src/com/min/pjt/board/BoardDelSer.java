package com.min.pjt.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.min.pjt.MyUtils;
import com.min.pjt.db.BoardDAO;
import com.min.pjt.db.JdbcUpdateInterface;
import com.min.pjt.vo.BoardVO;
import com.min.pjt.vo.UserVO;

@WebServlet("/board/del")
public class BoardDelSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String strI_board = request.getParameter("i_board");
    	int i_board = MyUtils.parseStrToInt(strI_board);
    	System.out.println("i_board: " + i_board);
    	UserVO loginUser = MyUtils.getLoginUser(request);
    	if(loginUser == null) {
    		response.sendRedirect("/login");
    		return;
    	}
   
    	BoardVO param = new BoardVO();
    	param.setI_board(i_board);
    	param.setI_user(loginUser.getI_user());
    	
    	int result = BoardDAO.delBoard(param);
    	System.out.println("result : " + result);
    	response.sendRedirect("/board/list");
	}

}
