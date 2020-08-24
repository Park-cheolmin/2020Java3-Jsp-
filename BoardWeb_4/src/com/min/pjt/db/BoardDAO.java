package com.min.pjt.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.min.pjt.vo.BoardDomain;
import com.min.pjt.vo.BoardVO;
import com.min.pjt.vo.UserVO;

public class BoardDAO {
	
	public static BoardDomain selBoard(final int i_board) {
		final BoardDomain result = new BoardDomain();
		String sql =" select B.nm, A.i_user"
						+ " , A.title, A.ctnt, A.hits, to_char(A.r_dt, 'YY/MM/DD HH24:MI') as r_dt" 
						+ " FROM t_board4 A " 
						+ " INNER JOIN t_user B " 
						+ " ON A.i_user = B.i_user " 
						+ " WHERE i_board = ? ";
		
		int resultInt = JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setInt(1, i_board);
			}

			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				while(rs.next()) {
					result.setI_board(i_board);
					result.setI_user(rs.getInt("i_user"));
					result.setTitle(rs.getNString("title"));
					result.setCtnt(rs.getNString("ctnt"));
					result.setNm(rs.getNString("nm")); 
					result.setR_dt(rs.getNString("r_dt"));
					result.setHits(rs.getInt("hits"));
				}		
				return 1;
			}
			
		});
		
		return result;
		
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
				+ " SET  m_dt = sysdate "
				+ " , title = ? "
				+ " , ctnt = ? "
				+ " WHERE i_board = ? "
				+ " AND i_user = ? ";
				
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setNString(1, param.getTitle());
				ps.setNString(2, param.getCtnt());
				ps.setInt(3, param.getI_board());
				ps.setInt(4, param.getI_user());
				
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
	
	public static int addHits(final int i_board) {
		String sql = "UPDATE t_board4"
					+ " SET hits = hits + 1"
					+ "WHERE i_board = ?";
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				
				ps.setInt(1, i_board);
			}
			
		});
	}
	
	public static BoardVO likeDetailBoard(BoardVO param) {
		String sql = " SELECT A.i_board, A.title, A.ctnt, A.hits, A.i_user, A.r_dt, B.nm, DECODE(C.i_user, null, 0, 1) as yn_like "
					+ " FROM t_board4 A "
					+ " INNER JOIN t_user B "
					+ " ON A.i_user = B.i_user "
					+ " LEFT JOIN t_board4_like C "
					+ " ON A.i_board = C.i_board "
					+ " AND C.i_user = ? "
					+ " WHERE A.i_board = ? ";
		
		int resultInt = JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_user());
				ps.setInt(2, param.getI_board());
			}

			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				while(rs.next()) {
					String title = rs.getNString("title");
					
				}		
				return 1;
			}
			
		});
	}
	
}
