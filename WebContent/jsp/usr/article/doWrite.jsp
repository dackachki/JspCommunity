<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="com.sbs.example.jspCommunity.dto.Article"%>
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
<title>게시물 추가</title>
</head>
<body>
	<h1>게시물 추가</h1>
	<form action="/jspCommunity/usr/article/add">	
	<input type="hidden" name="memberId"  value="1">
	<div>
	제목:<input type="text" name="title">
	<br>
	<br>
	내용:<textarea name="body" value="내용 입력">  </textarea>
	<br>
	분류: <select name='boardId'>
  		<option value='' selected>-- 선택 --</option>
  		<option value="1">공지</option>
  		<option value='2'>방명록</option>
  		<option value='3'>자유</option>
		</select>
		<br>
		<br>
		<br>
		<br>
	<input value="추가" type="submit">
			

		
	</div>
</form>
</body>
</html>