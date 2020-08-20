<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리스트</title>
<style>
	table {border-collapse: collapse;} 
	th, td { border: 1px solid black;  }
	.itemRow:hover {
      background-color: #ecf0f1;
      cursor: pointer;
   }
</style>
</head>
<body>
	<div class = "container">
		<div>${loginUser.nm}님 환영합니다.</div>
		<div>
			<a href="/board/regmod">글쓰기</a>
		</div>
		<h1>리스트</h1>

			<table>
				<tr>
					<th>게시판 번호</th>
					<th>제목</th>
					<th>조회수</th>
					<th>유저 번호</th>
					<th>작성 날짜</th>
				</tr>
				<c:forEach items="${data}" var="item">
					<tr class="itemRow" onclick="moveDetail(${item.i_board})">
						<td>${item.i_board}</td>
						<td>${item.title}</td>
						<td>${item.hits}</td>
						<td>${item.i_user}</td>
						<td>${item.r_dt}</td>
					</tr>
				</c:forEach>
		</table>

		<script>
			function moveDetail(i_board) {
				console.log(i_board)
				location.href = "/board/detail?i_board=" + i_board;
			}
		</script>
	</div>
</body>
</html>