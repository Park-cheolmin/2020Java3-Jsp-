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
	#frm { text-align : center;}
	h1{text-align : center;}
	.login { display : inline-block;  margin-top : 10px;}
	button { margin-left :918px; margin-top : 10px;}
</style>
</head>
<body>
	<h1>👀HELLO👀</h1>
	<div class = "err">${msg }</div>
	<form id = "frm" action = "/login" method = "post">
		<div><label><input type = "text" name="user_id" placeholder = "아이디" value = "${data.user_id }"></label></div>
 		<div><label><input type = "password" name="user_pw" placeholder = "비밀번호" ></label></div>
 		<div class = "login"><input type = "submit" value="로그인"></div>
	</form>
	<a href = "/join"><button>회원가입</button></a>
</body>
</html>