<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세페이지</title>
<style>
	.err { color: #f00; }
</style>
</head>
<body>
	<div>
		<button onclick="doDel(${data.i_board})">삭제</button>
		<a href="/BoardMod?i_board=${data.i_board}"><button>수정</button></a>    <% //수정 삭제는 pk값을 필요로한다%>
	</div>
	<div>상세 페이지</div>
	<div>글번호 : ${data.i_board}</div>
	<div>제목 : ${data.title}</div>
	<div>내용 : ${data.ctnt}</div>
	<div>작성자 : ${data.i_student}</div>
	<script>
		function doDel(i_board) {
			if(confirm("삭제 하시겠습니까?")) {
				location.href ='/BoardDel?i_board=' + i_board
						
			}
			
		}
	</script>  
</body>
</html>