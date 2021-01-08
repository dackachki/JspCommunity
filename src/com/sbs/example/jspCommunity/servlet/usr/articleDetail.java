package com.sbs.example.jspCommunity.servlet.usr;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.dto.Article;
import com.sbs.example.jspCommunity.service.ArticleService;
import com.sbs.example.mysqlutil.MysqlUtil;

@WebServlet("/usr/article/detail")
public class articleDetail extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		if (req.getParameter("articleId") == null) {
			resp.getWriter().append("articleId를 입력해주세요.");
			return;
		}

		int articleId = Integer.parseInt(req.getParameter("articleId"));

		MysqlUtil.setDBInfo("127.0.0.1", "sbsst", "sbs123414", "jspCommunity");

		ArticleService articleService = Container.articleService;
		Article article = articleService.getArticleById(articleId);
		
		String boardName= articleService.getBoardByArticleId(article.boardId);
		
		MysqlUtil.closeConnection();
		
		req.setAttribute("article", article);
		req.setAttribute("boardName", boardName);

		RequestDispatcher rd = req.getRequestDispatcher("/jsp/usr/article/detail.jsp");
		rd.forward(req, resp);
	}
}