<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세페이지</title>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<style>
	.container {margin: 0 auto; width: 700px;}
	.ctnt { width: 700px; }
	a .listbutton { font-weight : bold;}
	h1 { font-size : 1.5em; widh: 300px; text-align: center;}
	.material-icons { color: red; }
	.pointerCursor {cursor: pointer;}
	.inlineDiv div { display: inline-block; }
	.time {float : right;}
	span{
	background: linear-gradient(to right, #e55d87, #5fc3e4);
	-webkit-background-clip: text;
  	-webkit-text-fill-color: transparent;
  	}
  	
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
		
		<br>
		<h1>자유게시판</h1>
		<hr>
		<div class="inlineDiv">제목 : ${data.title}
			<div class="time">${data.r_dt}</div>
		</div>
		<hr>
		<div class="ctnt">${data.ctnt}</div>
		<div class="inlineDiv">
			<div class = "num">게시판 번호 : ${data.i_board}</div>
		<div>작성자 : ${data.nm}</div>
		<div>조회수 : ${data.hits}</div>
		<div class = "pointerCursor" onclick="toggleLike(${data.yn_like})">
			<c:if test="${data.yn_like == 0 }">
				<span class="material-icons">favorite_border</span>
			</c:if>
			<c:if test="${data.yn_like == 1 }">
				<span class="material-icons">favorite</span>
			</c:if>
		</div>
		</div>
	</div>
	<script>
		function submitDel() {
			var chk = confirm("삭제 하시겠습니까?")
			if(chk) {
				delFrm.submit()
			}		
		}
		
		function toggleLike(yn_like) {
			location.href="/board/toggleLike?i_board=${data.i_board}&yn_like=" + yn_like
		}
		
	</script>
</body>
</html>