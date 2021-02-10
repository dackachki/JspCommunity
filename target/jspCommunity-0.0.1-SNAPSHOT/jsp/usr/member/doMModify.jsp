<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value="회원 정보 변경" />
<%@ include file="../../part/head.jspf"%>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>
<script>
	let DoModifyForm__submited = false;
	let DoModifyForm__checkedLoginId = "";
	// 폼 발송전 체크
	function DoModifyForm__submit(form) {
		if ( DoModifyForm__submited ) {
			alert('처리중입니다.');
			return;
		}
		
		form.loginPw.value = form.loginPw.value.trim();
	
		if ( form.loginPw.value.length > 0 ) {
			form.loginPwConfirm.value = form.loginPwConfirm.value.trim();
		
			if ( form.loginPwConfirm.value.length == 0 ) {
				alert('로그인 비밀번호 확인을 입력해주세요.');
				form.loginPwConfirm.focus();
				
				return;
			}
			
			if ( form.loginPw.value != form.loginPwConfirm.value ) {
				alert('로그인 비밀번호가 일치하지 않습니다.');
				form.loginPwConfirm.focus();
				
				return;
			}
		}
		
	
		if ( form.loginPw.value != form.loginPwConfirm.value ) {
			alert('로그인 비밀번호가 일치하지 않습니다.');
			form.loginPwConfirm.focus();
			
			return;
		}
		
			
		form.nickname.value = form.nickname.value.trim();
	
		if ( form.nickname.value.length == 0 ) {
			alert('별명을 입력해주세요.');
			form.nickname.focus();
			
			return;
		}
		
		form.email.value = form.email.value.trim();
	
		if ( form.email.value.length == 0 ) {
			alert('이메일을 입력해주세요.');
			form.email.focus();
			
			return;
		}
		form.cellphoneNo.value = form.cellphoneNo.value.trim();
		
		if ( form.cellphoneNo.value.length == 0 ) {
			alert('이메일을 입력해주세요.');
			form.cellphoneNo.focus();
			
			return;
		}
		form.loginPwReal.value = sha256(form.loginPw.value);
		form.loginPw.value ="";
		form.loginPwConfirm.value ="";	
		
		form.submit();
		DoModifyForm__submited = true;
	}

	
	</script>

<div class="con con-min-width flex flex-di-c flex-grow-1" style="height:68vh">
	<h1>${pageTitle}</h1>
	<form action="doMModify" method="POST"
		onsubmit="DoModifyForm__submit(this); return false;">
		<input type="hidden" name="loginPwReal" />

		<hr />

		<div>
			<div>비밀번호</div>
			<div>
				<input name="loginPw" type="password" maxlength="50"
					placeholder="변경할 비밀번호를 입력해주세요" />
			</div>
		</div>


		<hr />

		<div>
			<div>로그인 비밀번호 확인</div>
			<div>
				<input name="loginPwConfirm" type="password" maxlength="50"
					placeholder="비밀번호를 재입력 해주세요." />
			</div>
		</div>
		<hr>

		<div>
			<div>별명</div>
			<div>
				<input name="nickname" type="text" maxlength="50"
					value="${loginedMember.nickname}" />
			</div>
		</div>

		<hr />

		<div>
			<div>이메일</div>
			<div>
				<input name="email" type="email" maxlength="100"
					value="${loginedMember.email}" />
			</div>
		</div>

		<hr />
		<div>
			<div>핸드폰 번호</div>
			<div>
				<input name="cellphoneNo" type="tel" maxlength="100"
					value="${loginedMember.cellphoneNo}" />

			</div>
		</div>


		<hr />

		<div>

			<div>
				<input type="submit" value="변경" />
				<button type="button" onclick="history.back();">뒤로가기</button>
			</div>
		</div>
	</form>
</div>
<%@ include file="../../part/foot.jspf"%>