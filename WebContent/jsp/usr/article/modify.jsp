<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="com.sbs.example.jspCommunity.dto.Board"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
textarea {
	width: 200px;
	height: 200px;
}

.articlebody {
	display: inline-block;
}

.articlebody span {
	font-size: 1.5srem;
}
</style>

<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>${articleId}번 게시글 수정</title>
</head>
<body>

	<h1>${articleId}번 게시글 수정</h1>
	<form action="doModify" method="POST">


		<div class="title">
			<input type="hidden" name="articleId" value=${articleId}>
			<span> 제목: </span>

			<input type="text" name="title" maxlength="30"
				placeholder="${article.getTitle()}">
		</div>
		<hr />


		<div class="articlebody">

			<span>내용:</span>
			<textarea name="body" maxlength="3000"
				placeholder="${article.getBody()}">  </textarea>
		</div>

		<hr />
		<div>게시판 변경</div>
		<div>
			<select name="boardId">

				<option value="0">선택</option>
				<c:forEach var="board" items="${boards}">

					<option value="${board.getId()}">${board.getName()}</option>
				</c:forEach>
			</select>


			<input value="수정하기" type="submit">
			<button type="button" onclick="history.back();">뒤로가기</button>


		</div>
	</form>
</body>
</html>