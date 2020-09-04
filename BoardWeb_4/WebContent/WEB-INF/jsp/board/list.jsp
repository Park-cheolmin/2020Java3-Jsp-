<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판</title>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
<style>
	body { font-family: 'Noto Sans KR', sans-serif;}
		a {
		text-decoration: none;
		color:black;
	}
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
   
   .pageSelected { color:#FCD0BA; font-weight: bold; }
   .pagingFont { font-size: 1.3em; }
   .containerPImg {
   		display: inline-block;
   		width: 30px;
   		height: 30px;
   		border-radius: 50%;
   		overflow: hidden;
   }
   .pImg {
   		object-fit:cover;
   		height:100%;
   		width:100%;
   }
   .highlight {
   		color : pink;
   		font-weight: bold;
   		
   }
  .material-icons{
	background: linear-gradient(to right, #e55d87, #5fc3e4);
	-webkit-background-clip: text;
  	-webkit-text-fill-color: transparent;
  	}
  	
#likeListContainer {	
		display: none;		
		padding: 10px;		
		border: 1px solid #bdc3c7;
		position: absolute;
		left: 0px;
		top: 30px;
		width: 130px;
		height: 200px;
		overflow-y: auto;
		background-color: white !important;
	}	
		
	.profile {
		background-color: white !important;
		display: inline-block;	
		width: 25px;
		height: 25px;
	    border-radius: 50%;
	    overflow: hidden;
	}		
	
	.likeItemContainer {
		display: flex;
		width: 100%;
	}
	
	.likeItemContainer .nm {
		background-color: white !important;
		margin-left: 7px;
		font-size: 0.7em;
		display: flex;
		align-items: center;
	}

	
   
   
</style>
</head>
<body>
	
	<div class = "container">
		<div class = "user">${loginUser.nm}님 환영합니다.
			<a href="/profile"><button>프로필</button></a>
			<a href="/logout"><button  class = " outbutton ">로그아웃</button></a>
		</div>
		<div class = "user">
			<a href="/board/regmod"><button class = " writebutton ">글쓰기</button></a>
		</div>
		<div>
			<form id="selFrm" action="/board/list" method="get">
				<input type="hidden" name="page"  value="${param.page == null ?  1 : param.page}">
				<input type="hidden" name="searchText"  value="${param.searchText}">
				레코드 수 : 
				
				<select name="record_cnt" onchange="changeRecordCnt()">
					<c:forEach begin="10" end="30" step="10" var="item">
						<c:choose>
							<c:when test="${param.record_cnt == item}">
								<option value="${item}" selected>${item}개</option> 		 <%//레코드수를 설정하지않았을때는 기본값으로 10을 넣는다  selected는 한개만 들어가야하고 여러개더라도 맨위에꺼 선택%>
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
					<th>좋아요</th>
					<th></th>
					<th></th>
					<th>작성자</th>
					<th>작성 날짜</th>		
				</tr>
				<c:forEach items="${list}" var="item">
					<tr class="itemRow">
						<td onclick="moveDetail(${item.i_board})">${item.i_board}</td>
						<td onclick="moveDetail(${item.i_board})">${item.title}(${item.cmt_cnt})</td>
						<td>${item.hits}</td>
						<td><span onclick="getLikeList(${item.i_board}, ${item.like_cnt}, this)">${item.like_cnt}</span></td>
						<td>
								<c:if test="${item.yn_like == 0 }">
									<span class="material-icons">favorite_border</span>
								</c:if>
								<c:if test="${item.yn_like == 1 }">
									<span class="material-icons">favorite</span>
								</c:if>
						</td>
						<td>
							<div class="containerPImg">
								<c:choose>
									<c:when test="${item.profile_img != null}">
										<img class="pImg" src="/img/user/${item.i_user}/${item.profile_img}">
									</c:when>
									<c:otherwise>
										<img class="pImg" src="/img/default_profile.jpg">
									</c:otherwise>
								</c:choose>
							</div>
						</td>
						<td>	${item.nm}</td>
						<td>${item.r_dt}</td>
					</tr>
				</c:forEach>
		</table>
		<div>
			<form action="/board/list">
				<select name="searchType" >
					<option value="a" ${searchType == 'a' ? 'selected' : '' }>제목</option>
					<option value="b" ${searchType == 'b' ? 'selected' : '' }>내용</option>
					<option value="c" ${searchType == 'c' ? 'selected' : '' }>제목+내용</option>
				</select>
				<input type="search" name="searchText" value="${param.searchText}">
				<input type="submit" value="검색">
			</form>
		</div>

		<div class="fontCenter">
			<c:forEach begin="1" end="${pagingCnt}" var="item">
				<c:choose>
					<c:when test="${page == item}">
						<span class="pagingFont pageSelected">${item}</span>
					</c:when>
					<c:otherwise>
						<span class="pagingFont">
							<a href="/board/list?page=${item}&record_cnt=${param.record_cnt}&searchType=${searchType}&searchText=${param.searchText}">${item}</a>
						</span>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</div>
		
		<div id="likeListContainer"></div>
		<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
		<script>
		
		let beforeI_board = 0 
		function getLikeList(i_board, cnt, span) {
			console.log("i_board : " + i_board)
			if(cnt == 0) { return }
			
			if(beforeI_board == i_board) {
				likeListContainer.style.display = 'none'
				return
			} else if(beforeI_board != i_board) {
				beforeI_board = i_board
				likeListContainer.style.display = 'unset'
			}			
			
			
			const locationX = window.scrollX + span.getBoundingClientRect().left 
			const locationY = window.scrollY + span.getBoundingClientRect().top + 30
			
			likeListContainer.style.left = `\${locationX}px`
			likeListContainer.style.top = `\${locationY}px`
					
			likeListContainer.style.opacity = 1
			likeListContainer.innerHTML = ""
			
			
			axios.get('/board/like', {
				params: {
					i_board: i_board//key, 변수명이 같을때는 이렇게 사용, 원래는 i_board: i_board 이렇게 해야 함
				}
			}).then(function(res) {				
				if(res.data.length > 0) {					
					for(let i=0; i<res.data.length; i++) {
						const result = makeLikeUser(res.data[i])
						likeListContainer.innerHTML += result
					}
				}
			})
		}
			
			function makeLikeUser(one) {
				const img = one.profile_img == null ?
						'<img class="pImg" src="/img/default_profile.jpg">'
						:
						`<img class="pImg" src="/img/user/\${one.i_user}/\${one.profile_img}">`
				
				const ele = `<div class="likeItemContainer">
					<div class="profileContainer">
						<div class="profile">
							\${img}  
						</div>
					</div>
					<div class="nm">\${one.nm}</div>
				</div>`
				
				return ele
			}
			
		 	function changeRecordCnt() {
		 		selFrm.submit()
		 	}
			function moveDetail(i_board) {
				console.log(i_board)
				location.href = "/board/detail?page=${page}&record_cnt=${param.record_cnt}&searchType=${searchType}&searchText=${param.searchText}&i_board=" + i_board;
			}
		</script>
	</div>
</body>
</html>