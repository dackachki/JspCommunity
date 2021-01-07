<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%
List<Map<String, Object>> infoMapList = (List<Map<String, Object>>) request.getAttribute("infoMapList");
%>
<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>회원 리스트</title>
</head>
<body>
	<h1>공지 사항</h1>
	<%
	for (Map<String, Object> infoMap : infoMapList) {
	%>
	<div>
		번호 :
		<%=infoMap.get("id")%>
		<br />
		제목 :
		<%=infoMap.get("title")%>
		<br />
		내용 :
		<%=infoMap.get("body")%>
		<hr />
	</div>
	<%
	}
	%>
</body>
</html>