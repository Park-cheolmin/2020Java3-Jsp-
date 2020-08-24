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
		<caption>자유게시판</caption>
			<ul>
				<li>게시판 번호 : ${data.i_board}</li>
				<li>제목 : ${data.title}</li>
				<li class="ctnt">내용 : ${data.ctnt}</li>
				<li>작성자 : ${data.nm}</li>
				<li>작성 날짜 : ${data.r_dt}</li>
				<li>조회수 : ${data.hits}</li>
				
			</ul>
		
	</div>
	<script>
		function submitDel() {
			var chk = confirm("삭제 하시겠습니까?")
			if(chk) {
				delFrm.submit()
			}		
		}
		
		function like() {
			
		}
		
	</script>
</body>
</html>