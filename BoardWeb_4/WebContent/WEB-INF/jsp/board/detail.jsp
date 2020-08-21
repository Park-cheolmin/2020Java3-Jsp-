<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세페이지</title>
<style>
	.container {margin: 0 auto; width: 900px;}
	table, tr, td, th {border: 1px solid black; border-collapse: collapse;} 
	.ctnt { width: 400px; }
	a .listbutton { font-weight : bold;}
	caption { font-size : 1.5em; margin-bottom : 20px;}
</style>
</head>
<body>
	<div class = "container">
		<a href = "/board/list"><button class ="listbutton">리스트</button></a>
		<c:if test="${loginUser.i_user == data.i_user }">
			<a href="/board/regmod?i_board=${data.i_board}">수정</a>
			<form id="delFrm" action="/board/del" method="post">
				<input type="hidden" name="i_board" value="${data.i_board}">				
				<a href="#" onclick="submitDel()">삭제</a>
			</form>			
		</c:if>
		<table>
		<caption>자유게시판</caption>
			<tr>
				<th>게시판 번호</th>
				<th>제목</th>
				<th>내용</th>
				<th>작성자</th>
				<th>작성 날짜</th>
				<th>조회수</th>
			</tr>
			<tr>
				<td>${data.i_board}</td>
				<td>${data.title}</td>
				<td class="ctnt">${data.ctnt}</td>
				<td>${data.nm}</td>
				<td>${data.r_dt}</td>
				<td>${data.hits}</td>
			</tr>
		</table>
	</div>
	<script>
		function submitDel() {
			var chk = confirm("삭제 하시겠습니까?")
			if(chk) {
				delFrm.submit()
			}		
		}
		
	</script>
</body>
</html>