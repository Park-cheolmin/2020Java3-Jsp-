<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판</title>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
<style>
	body { font-family: 'Noto Sans KR', sans-serif;}
	.container {width: 700px; margin : 0 auto;  }
	table {border-collapse: collapse; width: 700px; margin: 0 auto; } 
	th, td { border: 1px solid black;  }
	th { background-color: #FEFAD4; }
	.itemRow:hover {
      background-color: #ecf0f1;
      cursor: pointer;
   }
   h1 {fontweight: bold; text-align: center;}
   .user {width : 100%; text-align: right; }

   a .writebutton {background : #FCD0BA; color:#765D69 ; margin-top : 20px; font-weight : bold; }
   a .outbutton {background : #FEFAD4; color: #765D69  font-weight : bold;}
</style>
</head>
<body>
	<div class = "container">
		<div class = "user">${loginUser.nm}님 환영합니다. <a href="/logout"><button  class = " outbutton ">로그아웃</button></a></div>
		<div class = "user">
			<a href="/board/regmod"><button class = " writebutton ">글쓰기</button></a>
		</div>
		<h1>자유게시판</h1>

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