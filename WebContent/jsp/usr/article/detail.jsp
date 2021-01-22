<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="pageTitle" value="게시물 상세정보" />

<%@ include file="../../part/head.jspf"%>

	<div class="con-min-width">
	<div class="con">

	<h1>게시물 정보</h1>
	
	
		번호 :
		${article.getId()}
		<br />
		작성날짜 :
		${article.getRegDate()}
		<br />
		갱신날짜 :
		${article.getUpdateDate()}
		<br />
		게시글 분류 :
		${boardName}
		<br />
		작성자 :
		${article.getExtra__nickname()} 
		<br />
		제목 :
		${article.getTitle()}
		<br />
		<div class="article-detail__body">
		내용 : ${article.getBody()}
		<hr />
	
	</div>
	</div>
	
	
	<div class="bot-menu con-min-width">
	<div class="con">
	<div>
	<a href="detail?articleId=${article.getId() -1}" class="highlight">
	&lt;이전글 |
	</a>
	<a href="detail?articleId=${article.getId() +1}" class="highlight">
	다음글 &gt;
	</a>
	<hr>
	</div>

		<a href="list?boardId=${article.getBoardId()}" class="highlight">리스트 </a>|
		
		<c:if test="${sessionScope.loginedMemberId == memberId}">
		<a href="modify?articleId=${article.getId()}" class="highlight">글 수정 </a>|
		<a class="highlight" href="delete?articleId=${article.getId()}" onclick="return confirm('게시물을 삭제하시겠습니까?');">글 삭제</a>
	</c:if>
		

</div>
	</div>
</div>
<script>
	function ArticleDetail__Body__init() {
		var viewer = new toastui.Editor.factory({
			el : document.querySelector('.article-detail__body'),
			initialValue : `${article-detail__body}`,
			viewer : true
		});
	}

	ArticleDetail__Body__init();
</script> 



<%@ include file="../../part/foot.jspf"%>