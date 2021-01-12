<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="com.sbs.example.jspCommunity.dto.Article"%>
<%
	Article article = (Article) request.getAttribute("article");
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
		<%=article.id%>
		<br />
		작성날짜 :
		<%=article.regDate%>
		<br />
		갱신날짜 :
		<%=article.updateDate%>
		<br />
		게시글 분류 :
		<%=boardName%>
		<br />
		작성자 :
		<%=article.extra__writer%>
		<br />
		제목 :
		<%=article.title%>
		<br />
		내용 :
		<%=article.body%>
		<hr />
	</div>
	<hr />
	<div>
	<a href="detail?articleId=<%=article.id -1%>">
	&lt;이전글
	</a>
	<a href="detail?articleId=<%=article.id +1%>">
	다음글 &gt;
	</a>
	
	</div>

	<div>
		<a href="list?boardId=<%=article.boardId%>">리스트로 이동</a>
		<a href="modify?articleId=<%=article.id%>">글 수정</a>
		<a href="delete?articleId=<%=article.id%>"
			onclick="return confirm('게시물을 삭제하시겠습니까?');">삭제</a>


	</div>



</body>
</html>