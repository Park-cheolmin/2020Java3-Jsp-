package com.min.board.db;

import java.sql.*;
import java.util.*;
import com.min.board.vo.BoardVO;

public class BoardDao {
	
	public static int insBoard(BoardVO param) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		//잘됬는지 안됬는지 확인만하는거라서 ResultSet rs = null; 이 필요없고 데이터가 필요할때는 쓴다
		
		String sql = " INSERT INTO t_board"
					+ " (i_board, title, ctnt, i_student)"
					+ " VALUES "
					+ " (seq_board.nextval, ? ,? ,? ) ";
		
		try {
			con = DbCon.getCon();
			ps = con.prepareStatement(sql);
			ps.setNString(1, param.getTitle());
			ps.setNString(2, param.getCtnt());
			ps.setInt(3, param.getI_student());
			
			//쿼리문실행  select일때만 executeQuery 나머진 update
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DbCon.close(con, ps);
		}
		return result;
	}
	
   public static List<BoardVO> selBoardList() {   
      List<BoardVO> list = new ArrayList();
      
      Connection con = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      
      String sql = " SELECT i_board, title, i_student "
                  + " FROM t_board "
                  + " ORDER BY i_board DESC ";
      
      try {
         con = DbCon.getCon();
         ps = con.prepareStatement(sql);
         rs = ps.executeQuery();         
         
         while(rs.next()) {
            int i_board = rs.getInt("i_board");
            String title = rs.getNString("title");
            int i_student = rs.getInt("i_student");
            
            BoardVO vo = new BoardVO();
            vo.setI_board(i_board);
            vo.setTitle(title);
            vo.setI_student(i_student);
            
            list.add(vo);
         }
         
      } catch(Exception e) {
         e.printStackTrace();
      } finally {
         DbCon.close(con, ps, rs);
      }
      
      return list;
   }
   
   public static BoardVO selBoard(BoardVO param) { //어떤글을 읽어야할지 db로부터 한레코드를 가지고와야하므로 파라미터 필요
	   
	   	BoardVO vo = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = " SELECT i_board, title, ctnt, i_student FROM t_board WHERE i_board = ? " ;
		
		
		try {
			con = DbCon.getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, param.getI_board());
			
			rs = ps.executeQuery(); 	
			
			while(rs.next()) {  //0줄 이면 false 1줄이면 true 이기때문에 if로 해도된다 (최대로 넘어올수 있는 줄이 1줄이다)
				
				int i_board = rs.getInt("i_board");
				String title = rs.getNString("title");
				String ctnt = rs.getNString("ctnt");
				int i_student = rs.getInt("i_student");
				
				vo = new BoardVO(); //자료가 없으면 null을 리턴하기위해 안에서 while문안에서 객체화
				
				vo.setI_board(i_board);
				vo.setTitle(title);
				vo.setCtnt(ctnt);
				vo.setI_student(i_student);
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
				DbCon.close(con, ps, rs);
		}
	   
		return vo;
   }
  
   public static int updBoard(BoardVO param) {
	   	int result = 0;
		
	   	Connection con = null;
		PreparedStatement ps = null;
		
		String sql =  " UPDATE t_board " +
					  " SET title = ? " +
					  " , ctnt = ? " +
					  " WHERE i_board = ? ";
		
		try {
			con = DbCon.getCon();
			ps = con.prepareStatement(sql);
			
			ps.setNString(1, param.getTitle());
			ps.setNString(2, param.getCtnt());
			ps.setInt(3, param.getI_board());

			result = ps.executeUpdate();
			
			
		} catch(Exception e){
			e.printStackTrace();
			System.out.println("수정 실패");
		} finally {
			DbCon.close(con, ps);
		}
	   
	   return result;
  }
   
   public static int delBoard(BoardVO param) { 
	   int result = 0;
	   
	   Connection con = null;
	   PreparedStatement ps = null;
	   
	   String sql = "Delete FROM t_board where i_board= ? " ;
	   
	   try {
		con = DbCon.getCon();
		ps = con.prepareStatement(sql);
		
		ps.setInt(1, param.getI_board());
		
		result = ps.executeUpdate();
		
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		DbCon.close(con, ps);
	}
	   
	   return result;
   }
}