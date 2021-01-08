package com.sbs.example.jspCommunity.service;

import java.util.List;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.dao.ArticleDao;
import com.sbs.example.jspCommunity.dto.Article;

public class ArticleService {
	ArticleDao articleDao;
	
	
	public ArticleService(){
		articleDao = Container.articleDao;
	}

	public List<Article> getForPrintArticlesByBoardId(int boardId) {
		return articleDao.getForPrintArticlesByBoardId(boardId);
	}

	public Article getArticleById(int articleId) {
		return articleDao.getArticleById(articleId);
	}

	public String getBoardByArticleId(int boardId) {
			return articleDao.getBoardByArticleId(boardId);
	}

}
