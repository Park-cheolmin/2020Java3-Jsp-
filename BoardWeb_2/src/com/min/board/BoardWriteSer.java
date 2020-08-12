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


@WebServlet("/BoardWrite")
public class BoardWriteSer extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String jsp = "/WEB-INF/view/BoardRegmod.jsp";
      request.getRequestDispatcher(jsp).forward(request, response);
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String title = request.getParameter("title");
      String ctnt = request.getParameter("ctnt");
      String strI_student = request.getParameter("i_student");
      
      // 단위테스트, 에러 빨리 찾을 수 있음, 값이 잘 넘어왔는지 꼭 테스트해보고 넘어가기
      System.out.println("title: "+title);
      System.out.println("ctnt: "+ctnt);
      System.out.println("strI_student: "+strI_student);
      
      BoardVO param = new BoardVO();
      param.setTitle(title);
      param.setCtnt(ctnt);
      param.setI_student(Utils.parseStrToInt(strI_student));
      
      int result = BoardDao.insBoard(param);
      System.out.println("result: "+ result);
      
      if(result == 1) { //정상
    	  response.sendRedirect("/BoardList");
         
      } else {
    	  request.setAttribute("msg", "값을 잘못입력했습니다.");
    	  doGet(request, response);
      }
      
      
   }

}