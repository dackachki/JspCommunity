<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="게시글 작성" />

<%@ include file="../../part/head.jspf"%>

<script
	src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>

<form class="con-min-width" action="doAdd" method="POST">
	<div class="con">
		<h1>게시글 작성</h1>
		<input type="hidden" name="body" /> <input type="hidden"
			name="memberId" value="1">
		<div>
			제목:<input type="text" name="title" maxlength="30"> <br>
			<br> 
			
		<div>
			<div>내용</div>
			<div>
				<script type="text/x-template"></script>
 				<div class="toast-ui-editor"></div>
			</div>
		</div>				
				
			</div>


			게시판 선택 
			<select name="boardId">
				<c:forEach var="board" items="${boards}">
					<option value="${board.getId()}">${board.getName()}</option>
				</c:forEach>
			</select> <input value="추가" type="submit"
					onclick="return confirm('게시물을 등록하시겠습니까?');">
			<button type="button" onclick="history.back();">뒤로가기</button>



		</div>
	</div>
</form>

<%@ include file="../../part/foot.jspf"%>