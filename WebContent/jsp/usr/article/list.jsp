<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="${boardName} 게시판 리스트" />

<%@ include file="../../part/head.jspf"%>

<hr>
	<section class="menu con-min-width">
	<div class="con">		
	
	<div>게시판 변경</div>

		<select name="boardId"
			onchange="if(this.value) location.href=(this.value)">
			<option>선택</option>
			<c:forEach var="board" items="${boards}">
				<option value="list?boardId=${board.getId()}">
					${board.getName()}</option>

			</c:forEach>
		</select>
		</div>
	</section>
	<hr />
	<div class="con-min-width">
	<div class="con">
	<h1>${boardName} 게시물 리스트</h1>

	<c:forEach var="article" items="${articles}">
		<div>
			번호 : ${article.getId()}
			<br />
			작성날짜 : ${article.getRegDate()}
			<br />
			갱신날짜 : ${article.getUpdateDate()}
			<br />
			작성자 : ${article.getExtra__nickname()}
			<br />
			제목 :
			<a class="highlight" href="detail?articleId=${article.getId()}">${article.getTitle()}</a>
			<hr />
		</div>
	</c:forEach>
</div>
</div>


</body>
</html>