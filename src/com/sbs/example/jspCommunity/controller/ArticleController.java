package com.sbs.example.jspCommunity.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sbs.example.Util.Util;
import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.dto.Article;
import com.sbs.example.jspCommunity.dto.Board;
import com.sbs.example.jspCommunity.dto.Member;
import com.sbs.example.jspCommunity.dto.Reply;
import com.sbs.example.jspCommunity.service.ArticleService;

public class ArticleController extends Controller {
	private ArticleService articleService;
	private List<Article> articlesInPage = new ArrayList<>();
	
	

	public ArticleController() {
		articleService = Container.articleService;

	}

	public String showList(HttpServletRequest req, HttpServletResponse resp) {
		articlesInPage.clear();
		Article article;
		int pageNo = Util.getAsInt(req.getParameter("pageNo"), 1);

		String searchKeyword = req.getParameter("searchKeyword");
		String searchKeywordType = req.getParameter("searchKeywordType");
		List<Board> boards = articleService.getAllBoards();
		int boardId = Integer.parseInt(req.getParameter("boardId"));
		Board board = articleService.getBoardById(boardId);
		int totalCount = articleService.getArticlesCountByBoardId(boardId, searchKeywordType, searchKeyword);
		List<Article> articles = articleService.getForPrintArticlesByBoardId(boardId, searchKeywordType, searchKeyword);
		

		double lastItem = articles.size();
		double divideV = 10;
		int page = (int) Math.ceil(lastItem / divideV);
		if (page <= 1) {
			page = 1;
		}

		int itemsInAPage = 10;
		int startPos = articles.size();

		startPos = (pageNo * itemsInAPage) - 9;
		if (pageNo == 1) {
			startPos = 0;
		}
		int endPos = startPos + itemsInAPage;

		if (endPos > articles.size()) {
			endPos = articles.size();
		}

		for (int i = startPos; i < endPos; i++) {
			article = articles.get(i);
			articlesInPage.add(article);

		}
		int pageBoxSize = 10;
		int previousPageBoxesCount = (page - 1) / pageBoxSize;
		int pageBoxStartPage = pageBoxSize * previousPageBoxesCount + 1;
		int pageBoxEndPage = pageBoxStartPage + pageBoxSize - 1;

		if (pageBoxEndPage > page) {
			pageBoxEndPage = page;
		}

		// 이전버튼 페이지 계산
		int pageBoxStartBeforePage = pageBoxStartPage - 1;
		if (pageBoxStartBeforePage < 1) {
			pageBoxStartBeforePage = 1;
		}

		// 다음버튼 페이지 계산
		int pageBoxEndAfterPage = pageBoxEndPage + 1;

		if (pageBoxEndAfterPage > page) {
			pageBoxEndAfterPage = page;
		}

		// 이전버튼 노출여부 계산
		boolean pageBoxStartBeforeBtnNeedToShow = pageBoxStartBeforePage != pageBoxStartPage;
		// 다음버튼 노출여부 계산
		boolean pageBoxEndAfterBtnNeedToShow = pageBoxEndAfterPage != pageBoxEndPage;

		req.setAttribute("page", page);
		req.setAttribute("board", board);
		req.setAttribute("boardName", board.getName());
		req.setAttribute("boards", boards);
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("articles", articlesInPage);
		req.setAttribute("pageNo", pageNo);
		req.setAttribute("pageBoxStartBeforeBtnNeedToShow", pageBoxStartBeforeBtnNeedToShow);
		req.setAttribute("pageBoxEndAfterBtnNeedToShow", pageBoxEndAfterBtnNeedToShow);
		req.setAttribute("pageBoxStartBeforePage", pageBoxStartBeforePage);
		req.setAttribute("pageBoxEndAfterPage", pageBoxEndAfterPage);
		req.setAttribute("pageBoxStartPage", pageBoxStartPage);
		req.setAttribute("pageBoxEndPage", pageBoxEndPage);

		if (searchKeyword == null) {

		} else {
			req.setAttribute("searchKeyword", searchKeyword);
			req.setAttribute("searchKeywordType", searchKeywordType);
		}
		return "usr/article/list";
	}

	public String detail(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		
		int articleId = Integer.parseInt(req.getParameter("articleId"));
		
		session.setAttribute("articleId", articleId);
		String boardName = articleService.getBoardNameById(articleId);
		int memberId = articleService.getMemberIdByArticleId(articleId);
		Article article = articleService.getArticleById(articleId);
	
		//로그인 안했을때 좋아요 싫어요 버튼 출력x
		if(session.getAttribute("loginedMemberId") != null) {
		int loginedMemberId = (int)session.getAttribute("loginedMemberId");
		boolean isLiked = articleService.decideLike(article.getId(),loginedMemberId);
		boolean isDisliked = articleService.decidedislike(article.getId(),loginedMemberId);
		//true = 데이터 없음 false = 데이터 있음
		req.setAttribute("isLiked", isLiked);
		req.setAttribute("isDisliked", isDisliked);
		}
		
		List<Reply> replies = articleService.getReplyByArticleId(article.getId());
		
		
		req.setAttribute("replies", replies);
		req.setAttribute("boardName", boardName);
		req.setAttribute("memberId", memberId);
		req.setAttribute("article", article);
		return "usr/article/detail";
	}

	public String add(HttpServletRequest req, HttpServletResponse resp) {

		Integer boardId = Integer.parseInt(req.getParameter("boardId"));
		Board board = articleService.getBoardById(boardId);
		List<Board> boards = articleService.getAllBoards();
		Member member = (Member) req.getAttribute("loginedMember");
		int memberId = member.getId();

		req.setAttribute("memberId", memberId);
		req.setAttribute("boardId", boardId);
		req.setAttribute("boards", boards);
		req.setAttribute("board", board);

		return "usr/article/doWrite";
	}

	public String doAdd(HttpServletRequest req, HttpServletResponse resp) {

		Member member = (Member) req.getAttribute("loginedMember");
		int memberId = member.getId();
		String title = req.getParameter("title");
		String body = req.getParameter("body");

		int boardId = Integer.parseInt(req.getParameter("boardId"));

		int newArticleId = articleService.add(title, body, memberId, boardId);

		return msgAndReplace(req, newArticleId + "번 게시물이 생성되었습니다.", String.format("detail?articleId=%d", newArticleId));
	}

	public String modify(HttpServletRequest req, HttpServletResponse resp) {

		int articleId = Integer.parseInt(req.getParameter("articleId"));
		Article article = articleService.getArticleById(articleId);

		int memberId = article.getMemberId();
		int loginedMemberId = (int) req.getAttribute("loginedMemberId");

		if (memberId != loginedMemberId) {
			return msgAndBack(req, "작성자만 수정할 수 있습니다.");
		}

		List<Board> boards = articleService.getAllBoards();

		req.setAttribute("article", article);
		req.setAttribute("memberId", memberId);
		req.setAttribute("boards", boards);
		req.setAttribute("articleId", articleId);
		return "usr/article/modify";

	}

	public String doModify(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();

		int articleId = Integer.parseInt(req.getParameter("articleId"));

		int memberId = articleService.getMemberIdByArticleId(articleId);
		String title = req.getParameter("title");
		String body = req.getParameter("body");
		int boardId = Integer.parseInt(req.getParameter("boardId"));

		int loginedMemberId = (int) session.getAttribute("loginedMemberId");
		System.out.printf("게시물번호:%d 제목:%s 내용 %s 게시판 번호:%d", articleId, title, body, boardId);
		if (memberId != loginedMemberId) {
			return msgAndBack(req, "작성자만 수정할 수 있습니다.");
		}

		if (boardId == 0) {
			return msgAndBack(req, "게시판을 선택해주세요.");

		}

		articleService.articleModify(articleId, title, body, boardId);
		return msgAndReplace(req, articleId + "번 게시물이 수정되었습니다.", String.format("list?boardId=1"));

	}

	public String delete(HttpServletRequest req, HttpServletResponse resp) {

		int articleId = Integer.parseInt(req.getParameter("articleId"));

		int loginedMemberId = (int) req.getAttribute("loginedMemberId");

		int memberId = articleService.getMemberIdByArticleId(articleId);
		if (memberId != loginedMemberId) {
			return msgAndBack(req, "작성자만 삭제할 수 있습니다.");
		}
		articleService.deleteArticle(articleId);
		return msgAndReplace(req, articleId + "번 게시물이 삭제되었습니다.", String.format("list?boardId=1"));

	}

	public String addLike(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		int memberId = (int) session.getAttribute("loginedMemberId");
		
		int articleId = Integer.parseInt(req.getParameter("articleId"));

		
		articleService.addLike(memberId, articleId, "article",1);
		
		return msgAndReplace(req, "좋아요 되었습니다", "detail?articleId="+articleId);
	}

	public String addDislike(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		int memberId = (int) session.getAttribute("loginedMemberId");

		int articleId = Integer.parseInt(req.getParameter("articleId"));

		
		articleService.addLike(memberId, articleId, "article",0);
		
		return msgAndReplace(req, "싫어요 되었습니다", "detail?articleId="+articleId);
	}
	
	public String removeLike(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		int memberId = (int) session.getAttribute("loginedMemberId");
	
		int articleId = Integer.parseInt(req.getParameter("articleId"));

	
		articleService.removeLike(memberId, articleId, "article",1);
	
		
		return msgAndReplace(req, "좋아요가 취소 되었습니다", "detail?articleId="+articleId);
	}

	

	public String removeDislike(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		int memberId = (int) session.getAttribute("loginedMemberId");
	
		int articleId = Integer.parseInt(req.getParameter("articleId"));

	
		articleService.removeLike(memberId, articleId, "article",0);
	
		
		return msgAndReplace(req, "싫어요가 취소 되었습니다", "detail?articleId="+articleId);
	}
	public String addreply(HttpServletRequest req, HttpServletResponse resp) {
		String rbody = req.getParameter("rbody");
		int memberId = Integer.parseInt(req.getParameter("loginedMemberId"));
		
		int articleId = Integer.parseInt(req.getParameter("articleId"));
		
		articleService.addReply(rbody,memberId,articleId);
		
		
		
		return msgAndReplace(req, "댓글이 추가되었습니다.","detail?articleId="+articleId);
		
	}

	public String deleteReply(HttpServletRequest req, HttpServletResponse resp) {
		int articleId = Integer.parseInt(req.getParameter("articleId"));
		
		int id = Integer.parseInt(req.getParameter("id"));
		
		articleService.deleteReply(id);
		
		return msgAndReplace(req, "댓글이 삭제되었습니다", "detail?articleId="+articleId);
	}

	public String addLikeR(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		int memberId = (int) session.getAttribute("loginedMemberId");
		
		int articleId = Integer.parseInt(req.getParameter("articleId"));

		
		articleService.addLike(memberId, articleId, "reply",1);
		
		return msgAndReplace(req, "댓글 좋아요가 되었습니다", "detail?articleId="+articleId);
	}

	public String addDislikeR(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		int memberId = (int) session.getAttribute("loginedMemberId");
		
		int articleId = Integer.parseInt(req.getParameter("articleId"));
		
		
		articleService.addLike(memberId, articleId, "reply",0);
		
		return msgAndReplace(req, "댓글 싫어요가 되었습니다", "detail?articleId="+articleId);
	
	}

	public String removeLikeR(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return null;
	}

	public String removeDislikeR(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return null;
	}

	public String articlehits(HttpServletRequest req, HttpServletResponse resp) {
		String hits = (String)req.getParameter("hits");
		int articleId = Integer.parseInt(req.getParameter("articleId"));
		
		if(hits.equals("true")) {
					
			articleService.updateHits(articleId);
	
		}	
	return null;
	}
	
	
	}

