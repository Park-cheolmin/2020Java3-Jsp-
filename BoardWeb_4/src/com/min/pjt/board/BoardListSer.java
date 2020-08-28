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
import com.min.pjt.MyUtils;
import com.min.pjt.ViewResolver;
import com.min.pjt.db.BoardDAO;
import com.min.pjt.vo.BoardDomain;
import com.min.pjt.vo.BoardVO;

@WebServlet("/board/list")
public class BoardListSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(MyUtils.isLogout(request)) {
			response.sendRedirect("/login");
			return;
		}
		String searchText = request.getParameter("searchText");
		searchText = (searchText == null ? "" : searchText);
		
		int page = MyUtils.getIntParameter(request, "page");
		page = (page == 0 ? 1 : page);
		
		int recordCnt = MyUtils.getIntParameter(request, "record_cnt");
		recordCnt = (recordCnt == 0 ? 10 : recordCnt);
		
		BoardDomain param = new BoardDomain();
		param.setRecord_cnt(recordCnt);
		param.setSearchText("%" + searchText + "%");
		int pagingCnt = BoardDAO.selPagingCnt(param);
		
		//이전 레코드수 값이 있고, 이전 레코드수보다 변경한 레코드 수가 더 크다면 마지막 페이지수로 변경
		if(page > pagingCnt) {  
			page = pagingCnt; //마지막 페이지 값으로 변경
		}
		request.setAttribute("page", page);
		System.out.println("page : " + page);
		
		int eIdx = page * recordCnt;
		int sIdx = eIdx - recordCnt;
		
		param.setsIdx(sIdx);
		param.seteIdx(eIdx);

		request.setAttribute("pagingCnt", pagingCnt);
		request.setAttribute("list", BoardDAO.selBoardList(param));
		
		ViewResolver.forward("board/list", request, response);
	}
}

