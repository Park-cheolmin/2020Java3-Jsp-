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
		
		int page = MyUtils.getIntParameter(request, "page");
		page = (page == 0 ? 1 : page);
		System.out.println("page : " + page);
		
		int eIdx = page * Const.RECORD_CNT;
		int sIdx = eIdx - Const.RECORD_CNT;
		
		BoardDomain param = new BoardDomain();
		
		param.setRecord_cnt(Const.RECORD_CNT); //한 페이지당  20개를 뿌릴꺼
		param.seteIdx(eIdx);
		param.setsIdx(sIdx);
		
		request.setAttribute("paging", page);
		request.setAttribute("pagingCnt", BoardDAO.selPagingCnt(param));
		request.setAttribute("data", BoardDAO.selBoardList(param));
		ViewResolver.forwardLoginChk("board/list", request, response);
		
		
	}
}

