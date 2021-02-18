package com.sbs.example.jspCommunity.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.dto.Article;
import com.sbs.example.jspCommunity.dto.Board;
import com.sbs.example.jspCommunity.service.ArticleService;

public class HomeController {
	ArticleService articleService;
	public HomeController() {
		articleService = Container.articleService;

	}
		
	public String main(HttpServletRequest req, HttpServletResponse resp) {
		List<Board> boards = articleService.getAllBoards();
		List<Article> noticeRecentArticle = articleService.getRecentArticles("notice");
		List<Article> guestBookRecentArticle = articleService.getRecentArticles("guestBook");
		List<Article> freeRecentArticle = articleService.getRecentArticles("free");
		List<Article> infoRecentArticle = articleService.getRecentArticles("info");
		
		
		req.setAttribute("boards", boards);
		req.setAttribute("noticeRecentArticle", noticeRecentArticle);
		req.setAttribute("guestBookRecentArticle", guestBookRecentArticle);
		req.setAttribute("freeRecentArticle", freeRecentArticle);
		req.setAttribute("infoRecentArticle", infoRecentArticle);
		
		return "usr/home/main";

	}
	
	
}
