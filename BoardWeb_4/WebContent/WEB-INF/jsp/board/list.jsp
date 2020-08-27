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

   a .writebutton {background : #FCD0BA; color:#765D69 ; margin-top : 20px; font-weight : bold; border: none; border-radius: 10px; width: 70px;}
   a .outbutton {background : #bbd2c5; color: #765D69  font-weight : bold; border: none; border-radius: 10px; width: 70px;}
   .fontCenter {text-align:center;}
   
   .currentPage { color:#FCD0BA; font-weight: bold; }
	a {
		text-decoration: none;
		color:black;
	}
	.pageFont {
		font-size: 1.3em;
		
	}
	
	
   
   
</style>
</head>
<body>
	
	<div class = "container">
		<div class = "user">${loginUser.nm}님 환영합니다. <a href="/logout"><button  class = " outbutton ">로그아웃</button></a></div>
		<div class = "user">
			<a href="/board/regmod"><button class = " writebutton ">글쓰기</button></a>
		</div>
		<div>
			<form id="selFrm" action="/board/list" method="get">
				<input type="hidden" name="page"  value="${param.page == null ?  1 : param.page}">
				레코드 수 : 
				
				<select name="record_cnt" onchange="changeRecordCnt()">
					<c:forEach begin="10" end="30" step="10" var="item">
						<c:choose>
							<c:when test="${param.record_cnt == item || param.record_cnt == null && item ==10 }">
								<option value="${item}" selected>${item}개</option>
							</c:when>
							<c:otherwise>
								<option value="${item}">${item}개</option>
							</c:otherwise>
						</c:choose>
						
					
					</c:forEach>
					
				</select>
			</form>
			
		</div>
		<h1>자유게시판</h1>

			<table>
				<tr>
					<th>게시판 번호</th>
					<th>제목</th>
					<th>조회수</th>
					<th>작성자</th>
					<th>작성 날짜</th>		
				</tr>
				<c:forEach items="${data}" var="item">
					<tr class="itemRow" onclick="moveDetail(${item.i_board})">
						<td>${item.i_board}</td>
						<td>${item.title}</td>
						<td>${item.hits}</td>
						<td>${item.nm}</td>
						<td>${item.r_dt}</td>
					</tr>
				</c:forEach>
		</table>

		<div class="fontCenter">
			<c:forEach  begin="1" end="${pagingCnt}" step="1" var="item">
				<c:choose>
					<c:when test="${param.page == item || (param.page == null && item == 1)}">
						<span class="pageFont currentPage ">${item}</span>
					</c:when>
					<c:otherwise>
						<span class="pageFont"><a href="/board/list?page=${item}">${item}</a></span>
					</c:otherwise>
				</c:choose>
				
			</c:forEach>
			
		</div>
		<script>
		 	function changeRecordCnt() {
		 		selFrm.submit()
		 	}
			function moveDetail(i_board) {
				console.log(i_board)
				location.href = "/board/detail?i_board=" + i_board;
			}
		</script>
	</div>
</body>
</html>