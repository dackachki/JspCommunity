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
import com.sbs.example.jspCommunity.service.ArticleService;

public class ArticleController {
	private ArticleService articleService;
	private List<Article> articlesInPage = new ArrayList<>();

	public ArticleController() {
		articleService = Container.articleService;

	}
	
	
	public String showList(HttpServletRequest req, HttpServletResponse resp) {
		articlesInPage.clear();
		Article article;
		int pageNo = Util.getAsInt(req.getParameter("pageNo"),1);
		
		
		String searchKeyword = req.getParameter("searchKeyword");
		String searchKeywordType = req.getParameter("searchKeywordType");
		List<Board> boards = articleService.getAllBoards();
		int boardId = Integer.parseInt(req.getParameter("boardId"));
		Board board = articleService.getBoardById(boardId);
		int totalCount = articleService.getArticlesCountByBoardId(boardId, searchKeywordType, searchKeyword);
		List<Article> articles = articleService.getForPrintArticlesByBoardId(boardId, searchKeywordType, searchKeyword);
		

		double lastItem = articles.size();
		double divideV= 10;
		int page =(int)Math.ceil(lastItem /divideV);
		if(page <= 1) {
			page = 1;
		}
		
		int itemsInAPage = 10;
		int startPos = articles.size();
		
		
		startPos = (pageNo * itemsInAPage) - 9;
		if(pageNo == 1) {
			startPos = 0;
		}
		int endPos = startPos+itemsInAPage;
		
		if (endPos > articles.size()) {
			endPos = articles.size();
		}
	
		for(int i = startPos;i < endPos;i++ ) {
			article = articles.get(i);
			articlesInPage.add(article);
			
		}
		
		System.out.printf("total page=%d\n",page);
		System.out.printf("current page=%d\n",pageNo);
		System.out.printf("start=%d\n",startPos);
		System.out.printf("end=%d\n",endPos);
		
		req.setAttribute("page", page);
		req.setAttribute("board", board);
		req.setAttribute("boardName", board.getName());
		req.setAttribute("boards", boards);
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("articles", articlesInPage);
		if(searchKeyword == null ) {
		
		}
		else {
			req.setAttribute("searchKeyword",searchKeyword);
			req.setAttribute("searchKeywordType",searchKeywordType);
		}
		return "usr/article/list";
	}

	public String detail(HttpServletRequest req, HttpServletResponse resp) {

		int articleId = Integer.parseInt(req.getParameter("articleId"));
		String boardName = articleService.getBoardNameById(articleId);
		int memberId = articleService.getMemberIdByArticleId(articleId);
		Article article = articleService.getArticleById(articleId);

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
		
		req.setAttribute("memberId",memberId);
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

		
		req.setAttribute("alertMsg", newArticleId + "번 게시물이 생성되었습니다.");
		req.setAttribute("replaceUrl", String.format("detail?articleId=%d", newArticleId));
		return "common/redirect";
	}

	public String modify(HttpServletRequest req, HttpServletResponse resp) {

		int articleId = Integer.parseInt(req.getParameter("articleId"));
		Article article = articleService.getArticleById(articleId);

		int memberId = article.getMemberId();
		int loginedMemberId = (int) req.getAttribute("loginedMemberId");

		if (memberId != loginedMemberId) {
			req.setAttribute("alertMsg", "작성자만 수정할 수 있습니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		List<Board> boards = articleService.getAllBoards();

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

		if (memberId != loginedMemberId) {
			req.setAttribute("alertMsg", "작성자만 수정할 수 있습니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		if (boardId == 0) {
			req.setAttribute("alertMsg", "게시판을 선택해주세요.");
			req.setAttribute("historyBack", true);
			return "common/redirect";

		}

		int modified = articleService.articleModify(articleId, title, body, boardId);
		req.setAttribute("alertMsg", articleId + "번 게시물이 수정되었습니다.");
		req.setAttribute("replaceUrl", String.format("list?boardId=1"));
		return "common/redirect";

	}

	public String delete(HttpServletRequest req, HttpServletResponse resp) {

		int articleId = Integer.parseInt(req.getParameter("articleId"));

		int loginedMemberId = (int) req.getAttribute("loginedMemberId");

		int memberId = articleService.getMemberIdByArticleId(articleId);
		if (memberId != loginedMemberId) {
			req.setAttribute("alertMsg", "작성자만 삭제할 수 있습니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}
		articleService.deleteArticle(articleId);
		req.setAttribute("alertMsg", articleId + "번 게시물이 삭제되었습니다.");
		req.setAttribute("replaceUrl", String.format("list?boardId=1"));
		return "common/redirect";

	}
}
