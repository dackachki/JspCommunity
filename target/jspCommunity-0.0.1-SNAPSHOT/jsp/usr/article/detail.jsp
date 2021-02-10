<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="게시물 상세정보" />

<%@ include file="../../part/head.jspf"%>

<script>
	function replyForm__submit(form) {

		form.rbody.value = form.rbody.value.trim();

		if (form.rbody.value.length == 0) {
			alert('댓글을 입력해주세요.');
			form.rbody.focus();

			return;
		}
		form.submit();
	}
	function article_Hits(){
		localStorage.setItem("hits", true);
		$.ajax({
		    url: "articlehits",
		    type: "GET",
		    data: {"hits": "true","articleId":"${articleId}"},
		    success: function (data) {
						localStorage.setItem("Updated",true);	            
		        }
		});
					
		}
		if(localStorage.getItem("Updated",true)){
			setTimeout(function(){localStorage.removeItem("Updated")}, 3000);
				}
		
		
		else {
			setTimeout(function(){article_Hits()}, 5000);
			
			
			}
	
	
	
</script>

<div class="con-min-width">
	<div class="con">

		<h1>게시물 정보</h1>

		<div class="article-info">
			<div>번호 : ${article.getId()}</div>
			<br />
			<div>작성날짜 : ${article.getRegDate()}</div>
			<br />
			<div>갱신날짜 : ${article.getUpdateDate()}</div>
			<div>조회수: ${article.getHitsCount()}</div>
			<br />
			<div>게시글 분류 : ${boardName}</div>
			<br />
			<div>작성자 : ${article.getExtra__nickname()}</div>
			<br /> 제목 : ${article.getTitle()}
			<hr />
			내용:

			<script type="text/x-template ">
	
		
			${article.getBody()}
			</script>
			<div class="toast-ui-viewer" style="height: 50vh; width: 100%">

				<hr />

			</div>
		</div>

		<c:if test="${sessionScope.loginedMemberId > 0}">

			<c:if test="${isLiked == true }">

				<button
					onclick="location.href='addLike?articleId=${article.getId()}'">
					좋아요 <i class="fas fa-thumbs-up"></i>
				</button>
			</c:if>
			<c:if test="${isLiked == false }">
				<button
					onclick="location.href='removeLike?articleId=${article.getId()}'">
					좋아요 취소 <i class="fas fa-thumbs-up"></i>
				</button>
			</c:if>
			<c:if test="${isDisliked == true }">

				<button
					onclick="location.href='addDislike?articleId=${article.getId()}'">
					싫어요 <i class="fas fa-thumbs-down"></i>
				</button>
			</c:if>

			<c:if test="${isDisliked == false }">

				<button
					onclick="location.href='removeDislike?articleId=${article.getId()}'">
					싫어요 취소 <i class="fas fa-thumbs-down"></i>
				</button>
			</c:if>


		</c:if>
	</div>
</div>



<div class="bot-menu con-min-width">
	<div class="con">
		<div>
			<a href="detail?articleId=${article.getId() -1}" class="highlight">
				&lt;이전글 | </a> <a href="detail?articleId=${article.getId() +1}"
				class="highlight"> 다음글 &gt; </a> &nbsp; <a
				href="list?boardId=${article.getBoardId()}" class="highlight">리스트
			</a>|

			<c:if test="${sessionScope.loginedMemberId == memberId}">
				<a href="modify?articleId=${article.getId()}" class="highlight">글
					수정 </a>|
		<a class="highlight" href="delete?articleId=${article.getId()}"
					onclick="return confirm('게시물을 삭제하시겠습니까?');">글 삭제</a>
			</c:if>
		</div>
		<hr>
	</div>
</div>


<div class="con-min-width">
	<div class="replytext con">
		<c:choose>

			<c:when test="${sessionScope.loginedMemberId > 0}">
				<h2>댓글 작성</h2>
				<form action="addReply" method="post"
					onsubmit="replyForm__submit(this); return false;">
					<input type="hidden" name="articleId" value="${article.id}">
					<input type="hidden" name="loginedMemberId"
						value="${sessionScope.loginedMemberId}">

					<textarea name="rbody" style="width: 100%">
	
	</textarea>

					<input type="submit" value="작성">

				</form>
			</c:when>
			<c:otherwise>
				<h2>로그인 후 댓글을 작성 할수 있습니다.</h2>
		
			</c:otherwise>
		</c:choose>
	<hr>
	</div>
</div>

<div class="con-min-width">
	<div class="replyList con">
		<c:forEach var="reply" items="${replies}">
			작성일자:  ${reply.regDate} / 
			${reply.rbody} /
			닉네임:${reply.writerNick}
			<c:if test="${sessionScope.loginedMemberId eq reply.memberId}">
				<button
					onclick="location.href='deleteReply?id=${reply.id}&articleId=${articleId}'">
					<i class="fas fa-minus"></i>
				</button>
			</c:if>
			<button
				onclick="location.href='addLikeR?articleId=${article.getId()}'">
				<i class="fas fa-thumbs-up"></i>
			</button>

			<button
			onclick="location.href='addDislikeR?articleId=${article.getId()}'">
				<i class="fas fa-thumbs-down"></i>
			</button>
			<br>
	<hr style="border:1px dashed">	
		</c:forEach>
	
	</div>
</div>


<%@ include file="../../part/foot.jspf"%>