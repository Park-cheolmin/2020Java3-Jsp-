package com.min.pjt.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.min.pjt.MyUtils;
import com.min.pjt.db.BoardCmtDAO;
import com.min.pjt.vo.BoardCmtVO;
import com.min.pjt.vo.UserVO;

@WebServlet("/board/cmt")
public class BoardCmtSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	//댓글 (삭제)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strI_board = request.getParameter("i_board");
		int i_cmt = MyUtils.getIntParameter(request, "i_cmt");
		
		UserVO loginUser = MyUtils.getLoginUser(request);
		
		BoardCmtVO param = new BoardCmtVO();
		param.setI_cmt(i_cmt);
		param.setI_user(loginUser.getI_user());
		
		BoardCmtDAO.delCmt(param);
		
		response.sendRedirect("/board/detail?i_board="+ strI_board);
		
	}
	
	//댓글 (등록/수정)   절대 화면뿌리는 용도는 x 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strI_cmt = request.getParameter("i_cmt");
		String strI_board = request.getParameter("i_board");
		String cmt = request.getParameter("cmt");
		String m_dt = request.getParameter("m_dt");

		
		int i_board = MyUtils.parseStrToInt(strI_board);
		
		UserVO loginUser = MyUtils.getLoginUser(request);
	
		BoardCmtVO param = new BoardCmtVO();
		param.setCmt(cmt);
		param.setI_user(loginUser.getI_user());
		
		switch (strI_cmt) {
		case "0":// 등록
			param.setI_board(i_board);
			BoardCmtDAO.insCmt(param);
			break;

		default:// 수정
			param.setI_cmt(MyUtils.parseStrToInt(strI_cmt)); 
			param.setM_dt(m_dt);
			BoardCmtDAO.updCmt(param);
			break;
		}
		response.sendRedirect("/board/detail?i_board="+ i_board);
	}

}
