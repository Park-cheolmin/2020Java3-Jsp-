<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>등록/수정</title>
</head>
<body>
	<div>
		<form id ="frm" action="regmod" method="post"  onsubmit = "return chk()">
			<div>제목 : <input type = "text" name = "title" value = "${data.title }"></div>
			<div>내용 : <textarea  name = "ctnt" value = "${data.ctnt }"></textarea></div>
			<div><input type = "submit" value = "등록"></div>
		</form>
	</div>
	<script>
		function chk() {
			if(frm.title.value == null) {
				alert("제목을 입력해주세요.")
				frm.title.focus()
				return false
			} else if (frm.ctnt.value == null) {
				alert("내용을 입력해주세요.")
				frm.ctnt.focus()
				return false
			}
		}
	</script>
</body>
</html>