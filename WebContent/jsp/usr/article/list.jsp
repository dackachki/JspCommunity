<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	
%>
<style>
section>div {
	display: inline-block;
}
</style>
<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>${boardName} 게시판 리스트</title>
</head>
<body>
	<section class="menu">
		<h2>게시물 설정</h2>
		<div>
			<a href="add?boardId=<%=request.getParameter("boardId")%>">게시물 작성</a>
		</div>
		<hr />
		<div>게시판 변경</div>

		<select name="boardId"
			onchange="if(this.value) location.href=(this.value)">
			<option>선택</option>
			<c:forEach var="board" items="${boards}">
				<option value="list?boardId=${board.getId()}">
					${board.getName()}</option>

			</c:forEach>
		</select>
	</section>
	<hr />
	<h1>${boardName} 게시물 리스트</h1>

	<c:forEach var="article" items="${articles}">
		<div>
			번호 : ${article.getId()}
			<br />
			작성날짜 : ${article.getRegDate()}
			<br />
			갱신날짜 : ${article.getUpdateDate()}
			<br />
			작성자 : ${article.getExtra__writer()}
			<br />
			제목 :
			<a href="detail?articleId=${article.getId()}">${article.getTitle()}</a>
			<hr />
		</div>
	</c:forEach>



</body>
</html>