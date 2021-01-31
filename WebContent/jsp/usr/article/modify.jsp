<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="com.sbs.example.jspCommunity.dto.Board"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="${articleId}번 게시글 수정" />

<%@ include file="../../part/head.jspf"%>

<script>
	let DoModifyForm__submited = false;
	let DoModifyForm__checkedLoginId = "";

	// 폼 발송전 체크
	function DoModifyForm__submit(form) {
		if (DoModifyForm__submited) {
			alert('처리중입니다.');
			return;
		}

		form.title.value = form.title.value.trim();

		if (form.title.value.length == 0) {
			alert('제목을 입력해주세요.');
			form.title.focus();

			return;
		}

		const editor = $(form).find('.toast-ui-editor').data(
				'data-toast-editor');
		const body = editor.getMarkdown().trim();

		if (body.length == 0) {
			alert('내용을 입력해주세요.');
			editor.focus();

			return;
		}

		form.body.value = body;

		form.submit();
		DoModifyForm__submited = true;
	}
</script>



<form class="con-min-width con" action="doModify" method="POST"
	onsubmit="DoModifyForm__submit(this); return false;">
<h1>${articleId}번게시글수정</h1>

	<div class="title">
		<input type="hidden" name="articleId" value="${articleId}"> <input
			type="hidden" name="body"> <span> 제목: </span> <input
			type="text" name="title" maxlength="30"
			value="${article.title}">
	</div>
	<hr />


	<div>
		<div>내용</div>
		<div>
			<script type="text/x-template">
				${article.body}
			</script>

			<div class="toast-ui-editor"></div>
		</div>
	</div>

	<hr />
	<div>게시판 변경</div>
	<div>
		<select name="boardId">

			<option value="0">선택</option>
			<c:forEach var="board" items="${boards}">

				<option value="${board.getId()}">${board.getName()}</option>
			</c:forEach>
		</select> <input value="수정하기" type="submit">
		<button type="button" onclick="history.back();">뒤로가기</button>


	</div>
</form>
<%@ include file="../../part/foot.jspf"%>