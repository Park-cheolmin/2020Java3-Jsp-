<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로필</title>
</head>
<style>
	.container {margin: 0 auto; width: 700px;}
	h1{text-align: center;}
	.btnContainer {  }
	.topBtn { float: left; }
	.pwBtn { float: right; }
	.imgContainer{ clear: both; margin-bottom: 20px; text-align: center; }	
	img{ width:500px; height:500px; border-radius: 50%; margin-left: auto; }
</style>

<body>
	<div class="container">
		<h1>프로필</h1>
		<div class="btnContainer">
			<a href="/board/list"><button class="topBtn">이전</button></a>
			<a href="/changePw"><button class="pwBtn">비밀번호 변경</button></a>   <!-- 버튼을 누르면 changePwSer으로 이동 -->
		</div>
		<div>
			<div class="imgContainer">
				<c:choose>
					<c:when test="${data.profile_img != null}">
						<img src="/img/user/${loginUser.i_user}/${data.profile_img}">
					</c:when>
					<c:otherwise>
						<img src="/img/default_profile.jpg">
					</c:otherwise>
				</c:choose>
			</div>
			<div>
				<form action="/profile" method="post" enctype="multipart/form-data">
					<div>
						<label>프로필 이미지: <input type="file" name="profile_img" accept="image/*"></label>
						<input type="submit" value="업로드">
					</div>
				</form>
			</div>
		
			<div>ID : ${data.user_id }</div>
			<div>이름 : ${data.nm }</div>
			<div>이메일 : ${data.email}</div>
			<div>가입일시 : ${data.r_dt}</div>
		</div>
	</div>
	<script>
		var proc = '${param.proc}'
		
		switch(proc) {
		case '1' :
			alert('비밀번호를 변경하였습니다.')
			break
		}
	</script>
</body>
</html>