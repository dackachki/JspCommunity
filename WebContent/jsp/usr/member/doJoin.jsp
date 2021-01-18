<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<style>
textarea {
	width: 200px;
	height: 200px;
}

input {
	text-align: right;
}
</style>
<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>게시글 작성</title>
</head>
<body>
	<h1>게시글 작성</h1>
	<form action="doJoin" method="POST">
		<div>
			이름:
			<input type="text" name="membername" maxlength="30">
			<br>
			아이디:
			<input type="text" name="loginId" maxlength="30">
			<br>
			비밀번호:
			<input type="password" name="loginPw" maxlength="30">
			<br>
			닉네임:
			<input type="text" name="nickname" maxlength="30">
			<br>
			이메일 주소:
			<input type="text" name="email" maxlength="30">
			<br>
	
			<input value="가입" type="submit"
				onclick="return alert('가입이 완료되었습니다.');">
			<button type="button" onclick="history.back();">뒤로가기</button>

		</div>
	</form>
</body>
</html>