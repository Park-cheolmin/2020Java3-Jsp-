package com.min.pjt.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.min.pjt.vo.BoardCmtVO;

public class BoardCmtDAO {
	
	public static int insCmt(BoardCmtVO param) {
		String sql =" INSERT INTO t_board4_cmt "
					+ " (i_cmt, i_board, i_user, cmt)"
					+ " VALUES "
					+ " (seq_board4_cmt.nextval, ?, ?, ?) ";
		
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_board());
				ps.setInt(2, param.getI_user());
				ps.setNString(3, param.getCmt());
				
			}
	
		});
		
	}
	
	public static int updCmt(BoardCmtVO param) {
		String sql  = " UPDATE t_board4_cmt "
					+ " set cmt = ?, m_dt = sysdate "
					+ " where i_cmt = ? "
					+ " AND i_user = ? ";
		
		return JdbcTemplate.executeUpdate(sql,  new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setNString(1, param.getCmt());
				ps.setInt(2, param.getI_cmt());
				ps.setInt(3, param.getI_user());
				
			}
			
		});
	}
	
	public static int delCmt(BoardCmtVO param) {
		String sql = "DELETE FROM t_board4_cmt "
					+ " WHERE i_cmt = ? AND i_user = ?";
		
		return JdbcTemplate.executeUpdate(sql,  new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_cmt());
				ps.setInt(2, param.getI_user());
				
			}
			
		});
	}
	
	public static List<BoardCmtVO> selCmtList(int i_board) {
		final List<BoardCmtVO> list = new ArrayList();
		String sql = " SELECT A.i_cmt, B.i_user, A.cmt, A.r_dt, A.m_dt, B.nm" //수정 삭제할때 i_user값이 필요하다 
				+ " from t_board4_cmt A "
				+ " INNER JOIN t_user B " //INNER LEFT 둘다 상관없다 
				+ " on A.i_user = B.i_user "
				+ " where A.i_board = ? "
				+ " order by A. i_cmt ";

		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setInt(1, i_board);
			}

			@Override
			public int executeQuery(ResultSet rs) throws SQLException {

				while (rs.next()) {
					int i_cmt = rs.getInt("i_cmt");
					int i_user = rs.getInt("i_user");
					String cmt = rs.getNString("cmt");
					String r_dt = rs.getNString("r_dt");
					String m_dt = rs.getNString("m_dt");
					String nm = rs.getNString("nm");

					BoardCmtVO param = new BoardCmtVO();
					param.setI_cmt(i_cmt);
					param.setI_user(i_user);
					param.setCmt(cmt);
					param.setR_dt(r_dt);
					param.setM_dt(m_dt);
					param.setNm(nm);


					list.add(param);
				}
				return 1;
			}
		});
		return list;
	}
	
	
	
}
