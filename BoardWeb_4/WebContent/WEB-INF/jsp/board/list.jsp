<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리스트</title>
<style>
	.container {margin: 0 auto;}
	table, tr, td, th {border: 1px solid black; border-collapse: collapse;} 
</style>
</head>
<body>
	<div class = "container">
		<h3>${loginUser.nm}님 환영합니다.</h3>
		<div>
			<a href="/board/regmod">글쓰기</a>
		</div>
		<h1>리스트</h1>
		<table>
			<caption>게시판리스트</caption>
			<tr>
				<th>게시판 번호</th>
				<th>제목</th>
				<th>조회수</th>
				<th>유저 번호</th>
				<th>작성 날짜</th>
			</tr>
			<c:forEach items="${data}" var="item">
				<tr>
					<td>${item.i_board }</td>
					<td>${item.title }</td>
					<td>${item.hits }</td>
					<td>${item.i_user }</td>
					<td>${item.r_dt }</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>