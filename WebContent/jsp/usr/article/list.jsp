<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="${boardName} 게시판 리스트" />

<%@ include file="../../part/head.jspf"%>


<section class="menu con-min-width">
	<div class="con">


		<div>
			<script>
				let DoSearchForm__submited = false;
				function DoSearchForm__submit(form) {
					if (DoSearchForm__submited) {
						alert('처리중입니다');
						return;
					}

					form.searchKeyword.value = form.searchKeyword.value.trim();

					if (form.searchKeyword.value.length == 0) {
						alert('검색어를 입력해주세요.');
						form.searchKeyword.focus();
						return;
					}

					form.submit();
					DoSearchForm__submited = true;
				}
			</script>
			<form action="" onsubmit="DoSearchForm__submit(this); return false;">
				<input type="hidden" name="boardId" value="${param.boardId}" /> <select
					name="searchKeywordType">
					<option value="titleAndBody">제목+본문</option>
					<option value="title">제목</option>
					<option value="body">본문</option>
				</select>
				<script>
					const param__searchKeywordType = '${param.searchKeywordType}';

					if (param__searchKeywordType) {
						$('select[name="searchKeywordType"]').val(
								param__searchKeywordType);
					}
				</script>
				<input value="${param.searchKeyword}" type="text"
					name="searchKeyword" placeholder="검색어를 입력해주세요." /> <input
					type="submit" value="검색" />
			</form>
		</div>
		<hr />
		<div>총 게시물 수 : ${totalCount}</div>
		<hr />

		<div class="con">
			<a>게시판 변경</a> <a>현재 게시판: ${board.getName()}</a>
		</div>



		<select name="boardId" onchange="if(this.value) location.href=(this.value)">
			<option>선택</option>
			<c:forEach var="Oboard" items="${boards}">
				<option value="list?boardId=${Oboard.getId()}">
					${Oboard.getName()}</option>

			</c:forEach>
		</select>
		<hr>
	</div>

</section>

<div class="con-min-width">
	<div class="con">
		<h1>${boardName}게시물리스트</h1>

		<hr>
		<c:forEach var="article" items="${articles}">
			<div>
				번호 : ${article.getId()} <br /> 작성날짜 : ${article.getRegDate()} <br />
				갱신날짜 : ${article.getUpdateDate()} <br /> 
				작성자 : ${article.getExtra__nickname()} <br /> 제목 : <a class="highlight"
					href="detail?articleId=${article.getId()}">${article.getTitle()}</a>
				<hr />
			</div>
		</c:forEach>
	</div>
</div>
<%@ include file="../../part/foot.jspf"%>