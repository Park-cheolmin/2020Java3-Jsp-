package com.min.pjt.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.min.pjt.vo.BoardVO;

public class BoardDAO {
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
		
		String sql = " SELECT i_board, title, hits, i_user, r_dt, m_dt "
				+ " FROM t_board4 ORDER BY i_board DESC "; //?가 없어서 prepared가 필요없음
		
		int result = JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {			}

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
}
