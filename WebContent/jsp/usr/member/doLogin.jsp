<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pageTitle" value="로그인" />
<%@ include file="../../part/head.jspf"%>
<script>
	let DoLoginForm__submited = false;
	function DoLoginForm__submit(form) {
		if (DoLoginForm__submited) {
			alert('처리중입니다.');
			return;
		}

		form.loginId.value = form.loginId.value.trim();

		if (form.loginId.value.length == 0) {
			alert('로그인 아이디를 입력해주세요.');
			form.loginId.focus();

			return;
		}

		form.loginPw.value = form.loginPw.value.trim();

		if (form.loginPw.value.length == 0) {
			alert('로그인 비밀번호를 입력해주세요.');
			form.loginPw.focus();

			return;
		}
		form.loginPwReal.value = sha256(form.loginPw.value);

		form.loginPw.value = "";
		form.submit();
		DoLoginForm__submited = true;
	}
</script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>



<form class="con-min-width" action="doLogin" method="POST"
	onsubmit="DoLoginForm__submit(this); return false;">
	<input type="hidden" name="loginPwReal" />
	<input type="hidden" name="afterLoginURL" value = "${param.afterLoginURL}" />
	<div class="con" style="height:74vh">
		<h1>${pageTitle}</h1>
		<hr />
		<div>
			<div>로그인 아이디</div>
			<div>
				<input name="loginId" type="text" maxlength="50"
					placeholder="로그인 아이디를 입력해주세요." />
			</div>
		</div>

		<hr />

		<div>
			<div>로그인 비번</div>
			<div>
				<input name="loginPw" type="password" maxlength="50"
					placeholder="로그인 비밀번호를 입력해주세요." />
			</div>
		</div>

		<hr />

		<div>

			<div>
				<input type="submit" value="로그인" />
				<button type="button" onclick="history.back();">뒤로가기</button>
			</div>
		</div>
	</div>
</form>

<%@ include file="../../part/foot.jspf"%>