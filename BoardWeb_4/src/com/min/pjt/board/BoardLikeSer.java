package com.min.pjt.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.min.pjt.MyUtils;
import com.min.pjt.db.BoardDAO;
import com.min.pjt.vo.BoardDomain;

@WebServlet("/board/like")
public class BoardLikeSer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//리스트 가져오기
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int i_board = MyUtils.getIntParameter(request, "i_board");
		
		List<BoardDomain> likeList = BoardDAO.selBoardLikeList(i_board);
		
		Gson gson = new Gson();
		
		String json = gson.toJson(likeList);
		
		System.out.println("json : " + json);
		
		response.setCharacterEncoding("UTF-8"); //한글 안깨지기 위해 사용
		response.setContentType("application/json"); //현재보내고있는 자료가 Json이다라고 response에 보냄
		PrintWriter out = response.getWriter();
		out.print(json);
	}
	
	//좋아요 처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
