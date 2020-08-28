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
	a { text-decoration: none;}
	a .listbutton { font-weight : bold;}
	h1 { font-size : 1.5em; widh: 300px; text-align: center;}
	.pointerCursor {cursor: pointer;}
	.inlineDiv div { display: inline-block; }
	.time {float : right;}
	.material-icons{
	background: linear-gradient(to right, #e55d87, #5fc3e4);
	-webkit-background-clip: text;
  	-webkit-text-fill-color: transparent;
  	}
  	.marginTop30 {
  		margin-top: 30px;
  		
  	}
  	#cmt { width:630px;}
  	
</style>
</head>
<body>
	<div class="container">
		<button><a href="/board/list?page=${param.page}&record_cnt=${param.record_cnt}&searchText=${param.searchText}">리스트</a></button>
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
		<div class="inlineDiv">
			제목 : ${data.title}
			<div class="time">${data.r_dt}</div>
		</div>
		<hr>
		<div class="ctnt">${data.ctnt}</div>
		<div class="inlineDiv">
			<div class="num">게시판 번호 : ${data.i_board}</div>
			<div>작성자 : ${data.nm}</div>
			<div>조회수 : ${data.hits}</div>
			<div class="pointerCursor" onclick="toggleLike(${data.yn_like})">
				<c:if test="${data.yn_like == 0 }">
					<span class="material-icons">favorite_border</span>
				</c:if>
				<c:if test="${data.yn_like == 1 }">
					<span class="material-icons">favorite</span>
				</c:if>
			</div>
		</div>


		<div class="marginTop30">
			<form id="cmtFrm" action="/board/cmt" method="post">
				<input type="hidden" name="i_cmt" value="0">						<%//0이넘어가거나 빈칸이넘어가면 등록  아니면 수정%>
				<input type="hidden" name="i_board" value="${data.i_board}">
				<div>
					<input type="text" id="cmt" name="cmt" placeholder="댓글내용">
					<input type="submit" id="cmtSubmit" value="등록">
					<input type="button" value="취소" onclick="clkCmtCancel()">
				</div>
			</form>
		</div>
		<div class="marginTop30">
			<table>
				<tr>
					<th>내용</th>
					<th>글쓴이</th>
					<th>등록일</th>
					<th>비고</th>
				</tr>
				<c:forEach items="${cmtList}" var="item">
					<tr>
						<td>${item.cmt}</td>
						<td>${item.nm}</td>
						<td>${item.m_dt}</td>
						<td>
							<c:if test="${item.i_user == loginUser.i_user}">
								<button onclick="clkCmtDel(${item.i_cmt})">삭제</button>
								<button onclick="clkCmtMod(${item.i_cmt}, '${item.cmt}')">수정</button>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

	<script>
		function clkCmtDel(i_cmt) {
			if(confirm('삭제 하시겠습니까?')) {
				location.href = '/board/cmt?i_board=${data.i_board}&i_cmt=' + i_cmt
			}
		}
		
	
		function clkCmtCancel() {
			cmtFrm.i_cmt.value = 0
			cmtFrm.cmt.value = ''
			cmtSubmit.value = '전송'
		}
		
		function clkCmtMod(i_cmt, cmt) {
			console.log('i_cmt : ' + i_cmt)
			cmtFrm.i_cmt.value = i_cmt
			console.log('cmtFrm.i_cmt.value : ' + cmtFrm.i_cmt.value)
			cmtFrm.cmt.value = cmt
			
			
			cmtSubmit.value = '수정'
		}
	
		function submitDel() {
			var chk = confirm("삭제 하시겠습니까?")
			if(chk) {
				delFrm.submit()
			}		
		}
		
		function toggleLike(yn_like) {
			location.href="/board/toggleLike?page=${param.page}&record_cnt=${param.record_cnt}&searchText=${param.searchText}&i_board=${data.i_board}&yn_like=" + yn_like
		}
		
	
		
		
	</script>
</body>
</html>