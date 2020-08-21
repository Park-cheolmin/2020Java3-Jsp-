<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${data == null? '등록' : '수정' }</title>
<style>
	#frm { text-align : center; margin-top: 300px;}
	.input { font-size : 1.5em;}
	.submit {  margin-top : 30px;}
</style>
</head>
<body>
	<div class = " container ">
		<form id ="frm" action="regmod" method="post"  onsubmit = "return chk()">
			<div><input type = "hidden" name = "i_board" value = "${data.i_board}"></div>
			<div class = "input">제목 : <input type = "text" name = "title" value = "${data.title}" ></div>
			<div class = "input">내용 : <textarea  name = "ctnt">${data.ctnt}</textarea></div>
			<div class = "submit"><input type = "submit" value ="${data == null ? '등록' : '수정' }"></div>
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