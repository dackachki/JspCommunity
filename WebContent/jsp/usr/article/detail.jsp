<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<%@ page import="com.sbs.example.jspCommunity.dto.Article"%>
<%
	
String boardName = (String) request.getAttribute("boardName");
%>
<!doctype html>
<html lang="ko">
<head>

<meta charset="UTF-8" />
<title>게시물 정보</title>
</head>
<body>
	<h1>게시물 정보</h1>

	<div>
		번호 :
		${article.getId()}
		<br />
		작성날짜 :
		${article.getRegDate()}
		<br />
		갱신날짜 :
		${article.getUpdateDate()}
		<br />
		게시글 분류 :
		<%=boardName%>
		<br />
		작성자 :
		${article.getExtra__writer()}
		<br />
		제목 :
		${article.getTitle()}
		<br />
		내용 :
			${article.getBody()}
		<hr />
	</div>
	<hr />
	<div>
	<a href="detail?articleId=${article.getId() -1}">
	&lt;이전글
	</a>
	<a href="detail?articleId=${article.getId() +1}">
	다음글 &gt;
	</a>
	
	</div>

	<div>
		<a href="list?boardId=${article.getBoardId()}">리스트로 이동</a>
		<a href="modify?articleId=${article.getId()}">글 수정</a>
		<a href="delete?articleId=${article.getId()}"
			onclick="return confirm('게시물을 삭제하시겠습니까?');">삭제</a>


	</div>



</body>
</html>