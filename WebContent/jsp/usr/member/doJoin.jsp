<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value="회원가입" />
<%@ include file="../../part/head.jspf"%>

<script src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>	
	<script>
	let DoJoinForm__submited = false;
	let DoJoinForm__checkedLoginId = "";
	
	// 로그인 아이디 중복체크
	function DoJoinForm__checkLoginIdDup(el) {
		const from = $(el).closest('form').get(0);
		const loginId = from.loginId.value;
		
		$.get(
			"getLoginIdDup",
			{
				loginId
			},
			
			function(data) {
				
				if ( data == "YES" ) {
					
					alert("해당 로그인 아이디는 사용가능합니다.");
					DoJoinForm__checkedLoginId = loginId;
				}
				else {
					alert("해당 로그인 아이디는 사용중입니다.");
				}
			},
			"html"
		);
	}
	
	
	// 폼 발송전 체크
	function DoJoinForm__submit(form) {
		if ( DoJoinForm__submited ) {
			alert('처리중입니다.');
			return;
		}
	
		form.loginId.value = form.loginId.value.trim();
	
		if ( form.loginId.value.length == 0 ) {
			alert('로그인 아이디를 입력해주세요.');
			form.loginId.focus();
			
			return;
		}
		
		if ( form.loginId.value != DoJoinForm__checkedLoginId ) {
			alert('로그인 아이디 중복검사를 해주세요.');
			form.btnLoginIdDupCheck.focus();
			return false;
		}
		
		form.loginPw.value = form.loginPw.value.trim();
	
		if ( form.loginPw.value.length == 0 ) {
			alert('로그인 비밀번호를 입력해주세요.');
			form.loginPw.focus();
			
			return;
		}
		
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
		
		form.name.value = form.name.value.trim();
	
		if ( form.name.value.length == 0 ) {
			alert('이름을 입력해주세요.');
			form.name.focus();
			
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
			alert('핸드폰 번호를 입력해주세요.');
			form.cellphoneNo.focus();
			
			return;
		}
		form.loginPwReal.value = sha256(form.loginPw.value);
		form.loginPw.value ="";
		form.loginPwConfirm.value ="";	
		
		form.submit();
		DoJoinForm__submited = true;
	}

	
	</script>
	
	<div class="con con-min-width" style="height:74vh">
	<h1>${pageTitle}</h1>
	<form action="doJoin" method="POST" onsubmit="DoJoinForm__submit(this); return false;">
		<hr />
		<div>
			<div>로그인 아이디</div>
			<div>
				<input name="loginId" type="text" maxlength="50"
					placeholder="로그인 아이디를 입력해주세요." />
					<input type="hidden" name="loginPwReal" />
				
				<button onclick="DoJoinForm__checkLoginIdDup(this);" name="btnLoginIdDupCheck" type="button">중복체크</button>
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
			<div>로그인 비밀번호 확인</div>
			<div>
				<input name="loginPwConfirm" type="password" maxlength="50"
					placeholder="로그인 비밀번호 확인을 입력해주세요." />
			</div>
		</div>

		<hr />

		<div>
			<div>이름</div>
			<div>
				<input name="name" type="text" maxlength="50"
					placeholder="이름을 입력해주세요." />
			</div>
		</div>

		<hr />

		<div>
			<div>별명</div>
			<div>
				<input name="nickname" type="text" maxlength="50"
					placeholder="별명을 입력해주세요." />
			</div>
		</div>

		<hr />

		<div>
			<div>이메일</div>
			<div>
				<input name="email" type="email" maxlength="100"
					placeholder="이메일을 입력해주세요." />
			</div>
		</div>

		<hr />
		<div>
			<div>핸드폰 번호</div>
			<div>
			<input name="cellphoneNo" type="tel" maxlength="100"
					placeholder="전화번호를 입력해주세요." />
			
			</div>
		</div>
		

		<hr />

		<div>
			<div>가입</div>
			<div>
				<input type="submit" value="가입" />
				<button type="button" onclick="history.back();">뒤로가기</button>
			</div>
		</div>
	</form>
</div>
<%@ include file="../../part/foot.jspf"%>