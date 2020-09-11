package com.min.matzip;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.min.matzip.db.JdbcSelectInterface;
import com.min.matzip.db.JdbcTemplate;
import com.min.matzip.vo.CodeDomain;

public class CommonDAO {
	public static List<CodeDomain> selCodeList(final int i_m) {
		List<CodeDomain> list = new ArrayList(); //List는 interface(부모역할만한다), 제네릭을 주게되면 CodeDomain만 들어가고 나올수있다(그래서 형변환 필요 x)
		
		String sql=" SELECT i_m, cd, val FROM c_code_d WHERE i_m = ? ";
		
		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setInt(1, i_m);
				
			}

			@Override
			public void executeQuery(ResultSet rs) throws SQLException {
				while(rs.next()) {
					CodeDomain cd = new CodeDomain();
					cd.setCd(rs.getInt("cd"));
					cd.setVal(rs.getNString("val"));
					
					list.add(cd);
				}
				
			}
			
		});
		return list;
	}
}
