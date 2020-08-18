<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<style>
	.err {
		color : red;
	}
</style>
</head>
<body>
	<h1>로그인</h1>
	<div class = "err">${msg }</div>
	<form id = "frm" action = "/login" method = "post" onsubmit = "return chk()">
		<div><label><input type = "text" name="user_id" placeholder = "아이디" value = "${data.user_id }"></label></div>
 		<div><label><input type = "password" name="user_pw" placeholder = "비밀번호" ></label></div>
 		<div><input type = "submit" value="로그인"></div>
	</form>
	<a href = "/join">회원가입</a>
</body>
	<script>
		
	</script>
</html>