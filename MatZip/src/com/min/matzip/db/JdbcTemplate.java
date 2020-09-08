package com.min.matzip.db;
//공통된 작업을 여기서한다
import java.sql.*;

public class JdbcTemplate {
	//select용
	public static void executeQuery(String sql, JdbcSelectInterface jdbc) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DbManager.getCon();
			ps = con.prepareStatement(sql);
			jdbc.prepared(ps);
			rs = ps.executeQuery(); 
			jdbc.executeQuery(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbManager.close(con, ps, rs);
		}
	}
	
	//insert, update, delete에 쓸친구
	public static int executeUpdate(String sql, JdbcUpdateInterface jdbc) { //달리지는 내용을  JdbcUpdateInterface jdbc 에 주입한다, 그리고  JdbcUpdateInterface는update만 tlfgod
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DbManager.getCon();
			ps = con.prepareStatement(sql);
			jdbc.update(ps);
			
			result =ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbManager.close(con, ps);
		}
		return result;
	}
}
