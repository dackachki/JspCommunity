<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pageTitle" value="메인화면" />

<%@ include file="../../part/head.jspf"%>
<section class="con-min-width flex">

	<div class="con flex-grow-1" style="height:73vh">

	<div class="main-btns flex-grow-1">
	<c:if test="${sessionScope.loginedMemberId == null}">
	<button class="btn-login highlight" onclick="location.href='../member/login'"> 로그인 </button>
	<button class="btn-login highlight" onclick="location.href='../member/join'"> 회원가입 </button>
	
	</c:if>
	
	<c:if test="${sessionScope.loginedMemberId > 0}">
	<button class="btn-login highlight" onclick="location.href='../member/login'"> 로그아웃 </button>
	<button class="btn-login highlight" onclick="location.href='../member/MModify'"> 회원정보 수정 </button>
	</c:if>
	</div>			

		

		
		<div class="boardlist">
		<h1>최신 글 목록</h1>
		
			<div style="float: left; width: 33%;">
				<div style="text-align:center;" class="hidden md:inline-block">공지</div>
				<hr align="left" style="width:97%">
				<table align="center" width="100%" bordercolor="beige" class="hidden md:inline-block">
					
					<thead style="text-align: left;">
					
						<tr>
							<th>작성일</th>
							<th>제목</th>
							<th>조회수</th>
					</thead>

					<tbody>

						<c:forEach var="noticeRecentArticle"
							items="${noticeRecentArticle}">

							<tr>
								<td><span>${noticeRecentArticle.getRegDate()}</span></td>
								<td><span onclick="location.href='../article/detail?articleId=${noticeRecentArticle.getId()}'" class="highlight" style="cursor:pointer">${noticeRecentArticle.getTitle()}</span></td>
								<td><span>${noticeRecentArticle.getHitsCount()}</span></td>

							</tr>
						</c:forEach>
					</tbody>
				</table>

			</div>			
				<div style="float: left; width: 33%;">
				<div style="text-align:center;" class="hidden md:inline-block">정보</div>
				<hr align="left" style="width:97%">
				<table align="left" width="100%" bordercolor="beige"  class="hidden md:inline-block">
					<thead style="text-align: left;">
						<tr>
							<th>작성일</th>
							<th>제목</th>
							<th>조회수</th>
					</thead>

					<tbody>

						<c:forEach var="infoRecentArticle" items="${infoRecentArticle}">
						<tr>
								<td><span>${infoRecentArticle.getRegDate()}</span></td>
								<td><span onclick="location.href='../article/detail?articleId=${infoRecentArticle.getId()}'" class="highlight" style="cursor:pointer">${infoRecentArticle.getTitle()}</span></td>
								<td><span>${infoRecentArticle.getHitsCount()}</span></td>

							</tr>
						</c:forEach>

							</tbody>
				</table>
			</div>
			
			
			<div style="float: left; width: 33%;">
				<div style="text-align:center;" class="hidden md:inline-block">자유</div>
				<hr>
				<table align="left" width="100%" bordercolor="beige" class="hidden md:inline-block">
					<thead style="text-align: left;">
						<tr>
							<th>작성일</th>
							<th>제목</th>
							<th>조회수</th>
					</thead>

					<tbody>

						<c:forEach var="freeRecentArticle" items="${freeRecentArticle}">
						<tr>
								<td><span>${freeRecentArticle.getRegDate()}</span></td>
								<td><span onclick="location.href='../article/detail?articleId=${freeRecentArticle.getId()}'" class="highlight" style="cursor:pointer">${freeRecentArticle.getTitle()}</span></td>
								<td><span>${freeRecentArticle.getHitsCount()}</span></td>

							</tr>
						</c:forEach>
							</tbody>
				</table>
			</div>
			
			<div class="main md:hidden table" style="text-align:left">
 
 		<c:forEach var="board" items="${boards}">
  
    <input id="tab${board.id}" type="radio" name="tabs"> 
  	
    <label for="tab${board.id}">${board.name}</label>
 
     <section id="content${board.id}" style="margin-bottom:20px;">
        
    </section>
 	</c:forEach>
	</div>
	
		</div>
		
</section>

<%@ include file="../../part/foot.jspf"%>