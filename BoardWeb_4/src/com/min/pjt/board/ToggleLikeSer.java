package com.min.pjt.board;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.min.pjt.MyUtils;
import com.min.pjt.db.BoardDAO;
import com.min.pjt.user.LoginSer;
import com.min.pjt.vo.BoardDomain;
import com.min.pjt.vo.UserVO;


@WebServlet("/board/toggleLike")
public class ToggleLikeSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserVO loginUser = MyUtils.getLoginUser(request);
		int i_user = loginUser.getI_user();
		
		String strI_board = request.getParameter("i_board"); 
		String strYn_like = request.getParameter("yn_like");
		String page = request.getParameter("page");
		String record_cnt = request.getParameter("record_cnt");
		String searchText = request.getParameter("searchText");
		
		searchText = URLEncoder.encode(searchText, "UTF-8");
		
	
		
		int i_board = MyUtils.parseStrToInt(strI_board); 
		int yn_like = MyUtils.parseStrToInt(strYn_like);
		
		
		BoardDomain bd = new BoardDomain();
		bd.setI_user(i_user);
		bd.setI_board(MyUtils.parseStrToInt(strI_board));
		bd.setYn_like(MyUtils.parseStrToInt(strYn_like));
		
		BoardDAO.toggleLike(bd);
		
		String target = String.format("/board/detail?i_board=%s&page=%s&record_cnt=%s&searchText=%s", strI_board, page, record_cnt, searchText);
		response.sendRedirect(target);
		
	}

}
