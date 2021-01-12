<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="com.sbs.example.jspCommunity.dto.Board"%>

<%
int articleId= (Integer)request.getAttribute("articleId");
List<Board> boards = (List)request.getAttribute("boards");
%>
<style>
textarea{
width:200px;
height:200px;
}

</style>

<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title><%=articleId %>번 게시글 수정</title>
</head>
<body>
	<h1><%=articleId %>번 게시글 수정</h1>
	<form action="doModify" method="POST">	
	
	
	<div>
	<input type="hidden" name="articleId" value=<%=articleId %> >
	제목:<input type="text" name="title" maxlength="30">
	<br>
	<br>
	내용:<textarea name="body" maxlength="3000" placeholder="내용 입력">  </textarea>
	<br>
	<div>게시판 변경</div>
		<select name="boardId" >
		<option>선택</option>	
		<%
		for(Board board: boards){
		%>
		<option value=<%=board.id %>>
		<%=board.name%>
		</option>
			<%} 
			%>
		</select>
		
		
		<input value="수정하기" type="submit">
		<button type="button" onclick="history.back();">뒤로가기</button>
			
 		
	</div>
</form>
</body>
</html>