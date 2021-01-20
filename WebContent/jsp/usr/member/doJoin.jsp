<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../../part/head.jspf"%>


	
	<form class="con-min-width" action="doJoin" method="POST">
		<div class="con">
		<h1>회원  가입</h1>
			이름:<br>
			<input type="text" name="membername" maxlength="30" placeholder="">
			<br>
			아이디:<br>
			<input type="text" name="loginId" maxlength="30">
			<br>
			비밀번호:<br>
			<input type="password" name="loginPw" maxlength="30">
			<br>
			닉네임:<br>
			<input type="text" name="nickname" maxlength="30">
			<br>
			이메일 주소:<br>
			<input type="text" name="email" maxlength="30">
			<br>
	
			<input value="가입" type="submit"
				onclick="return alert('가입이 완료되었습니다.');">
			<button type="button" onclick="history.back();">뒤로가기</button>

		</div>
	</form>
</body>
</html>