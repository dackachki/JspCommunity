<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="com.sbs.example.jspCommunity.dto.Article"%>
<%@ page import="com.sbs.example.jspCommunity.dto.Board"%>
<%
List<Article>articles = (List<Article>)request.getAttribute("articles");
List<Board>boards = (List<Board>)request.getAttribute("boards");
String boardName = (String)request.getAttribute("boardName");

%>
<style>
section >div{
	display:inline-block;
}


</style>
<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title><%=boardName %> 게시판 리스트</title>
</head>
<body>
<section class="menu">
<h2>게시물 설정</h2>
<div>
		<a href="add?boardId=<%=request.getParameter("boardId")%>">게시물 작성</a>
	</div>
	<hr />
<div>게시판 변경</div>

		<select name="boardId"  onchange="if(this.value) location.href=(this.value)" >
			<option>선택</option>
		<%for(Board board: boards){
		%>
		<option value="list?boardId=<%=board.id%>"><%=board.name%></option>
			<%} 
			%>
		</select>
		</section>
<hr />
	<h1><%=boardName %> 게시물 리스트</h1>
	
	
	<%
	for (Article article : articles) {
	%>
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
		작성자 :
		<%=article.extra__writer%>
		<br />
		제목 :
		<a href="detail?articleId=<%=article.id%>"><%=article.title%></a>
		<hr />
	</div>
	<%
	}
	%>
	
		
	
</body>
</html>