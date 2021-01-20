package com.sbs.example.jspCommunity.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sbs.example.jspCommunity.dto.Article;
import com.sbs.example.jspCommunity.dto.Board;
import com.sbs.example.jspCommunity.dto.Member;
import com.sbs.example.mysqlutil.MysqlUtil;
import com.sbs.example.mysqlutil.SecSql;

public class ArticleDao {

	public List<Article> getForPrintArticlesByBoardId(int boardId) {
		List<Article> articles = new ArrayList<>();

		SecSql sql = new SecSql();
		sql.append("SELECT A.*");
		sql.append(", M.name AS extra__writer");
		sql.append(", B.name AS extra__boardName");
		sql.append(", B.code AS extra__boardCode");
		sql.append(", M.nickname AS extra__nickname");
		sql.append("FROM article AS A");
		sql.append("INNER JOIN `member` AS M");
		sql.append("ON A.memberId = M.id");
		sql.append("INNER JOIN `board` AS B");
		sql.append("ON A.boardId = B.id");
		if (boardId != 0) {
			sql.append("WHERE A.boardId = ?", boardId);
		}
		sql.append("ORDER BY A.id DESC");

		List<Map<String, Object>> articleMapList = MysqlUtil.selectRows(sql);

		for (Map<String, Object> articleMap : articleMapList) {
			articles.add(new Article(articleMap));
		}

		return articles;
	}

	public Article getArticleById(int articleId) {
		SecSql sql = new SecSql();
		sql.append("SELECT A.*");
		sql.append(", M.name AS extra__writer");
		sql.append(", B.name AS extra__boardName");
		sql.append(", B.code AS extra__boardCode");
		sql.append(", M.nickname AS extra__nickname");
		sql.append("FROM article AS A");
		sql.append("INNER JOIN `member` AS M");
		sql.append("ON A.memberId = M.id");
		sql.append("INNER JOIN `board` AS B");
		sql.append("ON A.boardId = B.id");
		if (articleId != 0) {
			sql.append("WHERE A.id = ?", articleId);
		}
		sql.append("ORDER BY A.id DESC");

		Map<String, Object> articleMap = MysqlUtil.selectRow(sql);

		Article article = new Article(articleMap);

		return article;

	}

	public Board getBoardByArticleId(int boardId) {

		SecSql sql = new SecSql();
		sql.append("SELECT * FROM BOARD WHERE ID = ?", boardId);

		Map<String, Object> boardMap = MysqlUtil.selectRow(sql);

		Board board = new Board(boardMap);

		return board;
	}

	public int add(String title, String body, int memberId, int boardId) {

		SecSql sql = new SecSql();
		sql.append("INSERT INTO ARTICLE");
		sql.append("SET TITLE = ?", title);
		sql.append(",regDate = now()");
		sql.append(",updateDate = now()");
		sql.append(",`body`= ?,memberId = ?", body, memberId);
		sql.append(",boardId = ?;", boardId);

		return MysqlUtil.insert(sql);
	}

	public int articleModify(int articleId, String title, String body, int boardId) {
		SecSql sql = new SecSql();
		sql.append("Update article set title =?", title);
		sql.append(", `body` = ?", body);
		sql.append(",updateDate = now()");
		sql.append(",boardId = ?", boardId);
		sql.append("where id = ?", articleId);

		return MysqlUtil.update(sql);

	}

	public List<Board> getAllBoards() {
		List<Board> boards = new ArrayList<>();

		SecSql sql = new SecSql();
		sql.append("SELECT * FROM BOARD");

		List<Map<String, Object>> boardMapList = MysqlUtil.selectRows(sql);

		for (Map<String, Object> boardMap : boardMapList) {
			boards.add(new Board(boardMap));
		}
		return boards;
	}

	public void deleteArticle(int articleId) {
		SecSql sql = new SecSql();
		sql.append("delete from article");
		sql.append("where id= ?", articleId);
		;

		MysqlUtil.delete(sql);

	}

	public int getMemberIdByArticleId(int articleId) {
		SecSql sql = new SecSql();
		sql.append("SELECT memberId");
		sql.append("FROM article AS A");
		sql.append("where id = ?",articleId);
		
		int memberId = MysqlUtil.selectRowIntValue(sql);

		

		return memberId;

	
	}

	
}
