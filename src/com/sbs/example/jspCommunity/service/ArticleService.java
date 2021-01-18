package com.sbs.example.jspCommunity.service;

import java.util.List;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.dao.ArticleDao;
import com.sbs.example.jspCommunity.dto.Article;
import com.sbs.example.jspCommunity.dto.Board;

public class ArticleService {
	private ArticleDao articleDao;

	public ArticleService() {
		articleDao = Container.articleDao;
	}

	public List<Article> getForPrintArticlesByBoardId(int boardId) {
		return articleDao.getForPrintArticlesByBoardId(boardId);
	}

	public int add(String title, String body, int memberId, int boardId) {
	return articleDao.add(title, body, memberId, boardId);
		
	}

	public Article getArticleById(int articleId) {
		
		return articleDao.getArticleById(articleId);
	}
	public Board getBoardById(int articleId) {
		return articleDao.getBoardByArticleId(articleId);
		
	}

	public int articleModify(int articleId, String title, String body, int boardId) {
		return articleDao.articleModify(articleId,title,body,boardId);
		
	}

	public List<Board> getAllBoards() {
		return articleDao.getAllBoards();
		
	}

	public String getBoardNameById(int articleId) {
	List<Board> boards = articleDao.getAllBoards();
	Article article = articleDao.getArticleById(articleId);
	for(Board board : boards) {
		if(article.getBoardId() == board.getId()) {
			return board.getName();
		}
		
	}
	return null;
	}

	public void deleteArticle(int articleId) {
		articleDao.deleteArticle(articleId);
		
	}
}
