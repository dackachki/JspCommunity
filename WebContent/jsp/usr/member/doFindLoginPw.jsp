<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pageTitle" value="비밀번호 찾기" />
<%@ include file="../../part/head.jspf"%>
<script>
	let DoFindForm__submited = false;
	function DoFindForm__submited(form) {
		if (DoFindForm__submited) {
			alert('처리중입니다.');
			return;
		}

		form.loginId.value = form.loginId.value.trim();

		if (form.loginId.value.length == 0) {
			alert('가입시 사용한 로그인 아이디를 입력해주세요.');
			form.loginId.focus();

			return;
		}

		form.name.value = form.name.value.trim();

		if (form.name.value.length == 0) {
			alert('회원 가입시 사용한 이름을 입력해주세요.');
			form.name.focus();

			return;
		}
		
		DoFindForm__submited = true;
	}
</script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>

<form class="con-min-width" action="doFindLoginPw" method="POST"
	onsubmit="DoFindForm__submited(this); return false;">
	<input type="hidden" name="loginPwReal" />
	<div class="con">
		<h1>${pageTitle}</h1>
		<hr />
		<div>
			<div>이름</div>
			<div>
				<input name="name" type="text" maxlength="50"
					placeholder="가입시 입력한 이름" />
			</div>
		</div>

		<hr />

		<div>
			<div>가입 아이디</div>
			<div>
				<input name="loginId" type="text" maxlength="50"
					placeholder="가입시 입력한 아이디" />
			</div>
		</div>

		<hr />

		<div>
			<div>확인</div>
			<div>
				<input type="submit" value="확인" />
				<button type="button" onclick="history.back();">뒤로가기</button>
			</div>
		</div>
	</div>
</form>

<%@ include file="../../part/foot.jspf"%>