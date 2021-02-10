<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>회원 리스트</title>
</head>
<body>
	<h1>회원 리스트</h1>
	<c:forEach var="member" items="${members}">	

	<div>
		번호 :${member.getId()}
		<br />
		이름 :${member.getName()}
		<br />
		닉네임 :${member.getNickname()}
		<hr />
	</div>
	
	</c:forEach>
</body>
</html>