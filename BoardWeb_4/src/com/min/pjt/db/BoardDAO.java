package com.min.pjt.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.min.pjt.vo.BoardVO;
import com.min.pjt.vo.UserVO;

public class BoardDAO {
	
	public static BoardVO selBoard(BoardVO param) {
		BoardVO vo = new BoardVO();
		String sql =" select B.nm, A.i_user"
						+ " , A.title, A.ctnt, A.hits, to_char(A.r_dt, 'YY/MM/DD HH24:MI') as r_dt" 
						+ " FROM t_board4 A " 
						+ " INNER JOIN t_user B " 
						+ " ON A.i_user = B.i_user " 
						+ " WHERE i_board = ? ";
		
		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_board());
			}

			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				while(rs.next()) {
					int i_user = rs.getInt("i_user");
					String title = rs.getNString("title");
					String ctnt = rs.getNString("ctnt");
					String nm = rs.getNString("nm");
					String r_dt = rs.getNString("r_dt");
					int hits = rs.getInt("hits");
					
					
					vo.setI_board(param.getI_board());
					vo.setI_user(i_user);
					vo.setTitle(title);
					vo.setCtnt(ctnt);
					vo.setNm(nm); 
					vo.setR_dt(r_dt);
					vo.setHits(hits);
					
					
				}		
				return 1;
			}
			
		});
		
		return vo;
		
	}
	
	
	public static int insBoard(BoardVO param) {
	
		String sql = " INSERT INTO t_board4"
					+ " (i_board, title, ctnt, i_user) "
					+ " VALUES "
					+ " (seq_board4.nextval, ?, ?, ? ) ";
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() { //new JdbcUpdateInterface를객체화

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setNString(1, param.getTitle());
				ps.setNString(2, param.getCtnt());
				ps.setInt(3, param.getI_user());
			}
			
			
		});
	}
	
	public static List<BoardVO> selBoardList() {
		final List<BoardVO> list = new ArrayList();  //list에 추가 삭제 가능, 주소값변경만불가능
		
		String sql = " SELECT i_board, title, hits, i_user, r_dt "
				+ " FROM t_board4 ORDER BY i_board DESC "; //?가 없어서 prepared가 필요없음
		
		int result = JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {}

			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				while(rs.next()) {
					int i_board = rs.getInt("i_board");
					String title = rs.getNString("title");
					int hits = rs.getInt("hits");
					int i_user = rs.getInt("i_user");
					String r_dt = rs.getNString("r_dt");
					
					BoardVO vo = new BoardVO();
					vo.setI_board(i_board);
					vo.setTitle(title);
					vo.setHits(hits);
					vo.setI_user(i_user);
					vo.setR_dt(r_dt);
					
					list.add(vo);
				}
				return 1;
			}
			
		});
		
		return list;
	}
	public static int updBoard(BoardVO param) {
		String sql = " UPDATE t_board4 "
				+ " SET title = ? "
				+ " ,ctnt = ? "
				+ " WHERE i_board = ? ";
				
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setNString(1, param.getTitle());
				ps.setNString(2, param.getCtnt());
				ps.setInt(3, param.getI_board());
				
			}
			
		});
		
	}
	public static int delBoard(final BoardVO param) {
		String sql = " DELETE FROM t_board4 WHERE i_board = ?  AND i_user = ? ";
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_board());
				ps.setInt(2, param.getI_user());
			}
		});
	}
}
