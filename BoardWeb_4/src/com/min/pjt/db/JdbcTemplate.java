package com.min.pjt.db;
//공통된 작업을 여기서한다
import java.sql.*;

public class JdbcTemplate {
	//select용
	public static int executeQuery(String sql, JdbcSelectInterface jdbc) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DbCon.getCon();
			ps = con.prepareStatement(sql);
			jdbc.prepared(ps);
			
			rs = ps.executeQuery(); 
			result = jdbc.executeQuery(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbCon.close(con, ps, rs);
		}
		return result;
	}
	
	//insert, update, delete에 쓸친구
	public static int executeUpdate(String sql, JdbcUpdateInterface jdbc) { //달리지는 내용을  JdbcUpdateInterface jdbc 에 주입한다, 그리고  JdbcUpdateInterface는update만 tlfgod
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DbCon.getCon();
			ps = con.prepareStatement(sql);
			jdbc.update(ps);
			
			result =ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbCon.close(con, ps);
		}
		return result;
	}
}
