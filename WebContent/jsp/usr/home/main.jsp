<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pageTitle" value="메인화면" />

<%@ include file="../../part/head.jspf"%>



<section class="con-min-width">
<div class="con">
<h1>${pageTitle}</h1>
<h1>안녕하세요.</h1>
<c:if test="${sessionScope.loginedMemberId > 0}">
		<div class="con">
	<h1>${sessionScope.loginedMemberNick}님 환영합니다.</h1>			
		</div>
		</c:if>


<hr>
<h3>게시판 바로가기</h#>

	<br>
	<a href="../article/list?boardId=1" class="highlight">공지사항</a>
	<br>
	<a href="../article/list?boardId=2"class="highlight">방명록</a>
	<br>
	<a href="../article/list?boardId=3"class="highlight">자유게시판</a>
	<br>






</div>
</section>
<%@ include file="../../part/foot.jspf"%>