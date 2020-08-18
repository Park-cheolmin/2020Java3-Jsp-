package com.min.pjt.db;


import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface JdbcUpdateInterface { //interface는 부모역할만 한다 (누구를 가리킬때만 쓸수 있다, 내용은 없어서 객체화 불가능)
	int update(PreparedStatement ps) throws SQLException; //앞에 생략된거 public, abstract
}
