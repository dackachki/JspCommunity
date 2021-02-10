<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pageTitle" value="메인화면" />

<%@ include file="../../part/head.jspf"%>




<section class="con-min-width flex">

	<div class="con flex-di-c flex-grow-1" style="height:68vh">

<h1>${pageTitle}</h1>

<h1 class="center">안녕하세요.</h1>

		<c:if test="${sessionScope.loginedMemberId > 0}">
		<div class="con center">
		<h1>${sessionScope.loginedMemberNick}님 환영합니다.</h1>			
		</div>
		</c:if>
		
		


<hr>
	<h2>게시판 바로가기</h2>
	
	<ul class="board-list">
	
	<li>
	<a href="../article/list?boardId=1&pageNo=1" class="highlight">공지사항</a>
		
	</li>
	<li>
	<a href="../article/list?boardId=2&pageNo=1"class="highlight">방명록</a>
	<br>
	</li>
	<li>
	<a href="../article/list?boardId=3&pageNo=1"class="highlight">자유게시판</a>
	<br>
</li>

</ul>




</div>
</section>
<%@ include file="../../part/foot.jspf"%>