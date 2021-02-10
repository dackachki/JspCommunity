package com.sbs.example.jspCommunity.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sbs.example.jspCommunity.dto.Article;
import com.sbs.example.jspCommunity.dto.Board;
import com.sbs.example.jspCommunity.dto.Member;
import com.sbs.example.jspCommunity.dto.Reply;
import com.sbs.example.mysqlutil.MysqlUtil;
import com.sbs.example.mysqlutil.SecSql;

public class ArticleDao {

	public List<Article> getForPrintArticlesByBoardId(int boardId, String searchKeywordType, String searchKeyword) {
		List<Article> articles = new ArrayList<>();

		SecSql sql = new SecSql();
		sql.append("SELECT A.*");
		sql.append(", M.name AS extra__writer");
		sql.append(", B.name AS extra__boardName");
		sql.append(", B.code AS extra__boardCode");
		sql.append("FROM article AS A");
		sql.append("INNER JOIN `member` AS M");
		sql.append("ON A.memberId = M.id");
		sql.append("INNER JOIN `board` AS B");
		sql.append("ON A.boardId = B.id");
		if (boardId != 0) {
			sql.append("WHERE A.boardId = ?", boardId);
		}

		if (searchKeyword != null) {
			if (searchKeywordType == null || searchKeywordType.equals("title")) {
				sql.append("AND A.title LIKE CONCAT('%', ? '%')", searchKeyword);
			} else if (searchKeywordType.equals("body")) {
				sql.append("AND A.body LIKE CONCAT('%', ? '%')", searchKeyword);
			} else if (searchKeywordType.equals("titleAndBody")) {
				sql.append("AND (A.title LIKE CONCAT('%', ? '%') OR A.body LIKE CONCAT('%', ? '%'))", searchKeyword,
						searchKeyword);
			}
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
		sql.append("SELECT * FROM board WHERE ID = ?", boardId);

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
		sql.append("SELECT * FROM board");

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
		sql.append("where id = ?", articleId);

		int memberId = MysqlUtil.selectRowIntValue(sql);

		return memberId;

	}

	public int getArticlesCountByBoardId(int boardId, String searchKeywordType, String searchKeyword) {
		SecSql sql = new SecSql();
		sql.append("SELECT COUNT(*) AS cnt");
		sql.append("FROM article AS A");
		sql.append("WHERE 1");

		if (boardId != 0) {
			sql.append("AND A.boardId = ?", boardId);
		}

		if (searchKeyword != null) {
			if (searchKeywordType == null || searchKeywordType.equals("title")) {
				sql.append("AND A.title LIKE CONCAT('%', ? '%')", searchKeyword);
			} else if (searchKeywordType.equals("body")) {
				sql.append("AND A.body LIKE CONCAT('%', ? '%')", searchKeyword);
			} else if (searchKeywordType.equals("titleAndBody")) {
				sql.append("AND (A.title LIKE CONCAT('%', ? '%') OR A.body LIKE CONCAT('%', ? '%'))", searchKeyword,
						searchKeyword);
			}
		}

		return MysqlUtil.selectRowIntValue(sql);
	}

	public void updateHits(int articleId) {
		SecSql sql = new SecSql();
		sql.append("UPDATE article");
		sql.append("SET hitsCount= hitsCount+1");
		sql.append("WHERE id = ? ;", articleId);

		MysqlUtil.update(sql);

	}

	public void addLike(int memberId, int articleId, String relType, int likeOrNot) {
		// 1이면 좋아요 0이면 싫어요
		if (likeOrNot == 1) {
			int likePoint = 1;

			SecSql sql = new SecSql();
			sql.append("INSERT INTO `like`");
			sql.append("SET relTypeCode = ?", relType);
			sql.append(",relId = ?", articleId);
			sql.append(",memberId=?", memberId);
			sql.append(",point=?;", likePoint);

			MysqlUtil.insert(sql);

		}

		else if (likeOrNot == 0) {

			int disLikePoint = -1;

			SecSql sql = new SecSql();
			sql.append("INSERT INTO `like`");
			sql.append("SET relTypeCode = ?", relType);
			sql.append(",relId = ?", articleId);
			sql.append(",memberId=?", memberId);
			sql.append(",point= ? ;", disLikePoint);

			MysqlUtil.insert(sql);

		}

	}

	public boolean decideLike(int id, int memberId) {
		SecSql sql = new SecSql();
		sql.append("SELECT * FROM `like`");
		sql.append("WHERE relTypeCode = ?", "article");
		sql.append("AND memberId = ?", memberId);
		sql.append("AND relId = ?", id);
		sql.append("AND `point` = ?", 1);

		if (MysqlUtil.selectRow(sql).isEmpty()) {
			return true;
		} else {
			return false;

		}

	}

	public boolean decidedislike(int id, int memberId) {
		SecSql sql = new SecSql();
		sql.append("SELECT * FROM `like`");
		sql.append("WHERE relTypeCode = ?", "article");
		sql.append("AND memberId = ?", memberId);
		sql.append("AND relId = ?", id);
		sql.append("AND `point` = ?", -1);
		// 싫어요 않함

		if (MysqlUtil.selectRow(sql).isEmpty()) {
			return true;
		}

		else {
			return false;

		}

	}

	public void removeLike(int memberId, int articleId, String relType, int likeOrNot) {
		// 1이면 좋아요 0이면 싫어요
		if (likeOrNot == 1) {
			int likePoint = 1;

			SecSql sql = new SecSql();
			sql.append("DELETE FROM `like`");
			sql.append("WHERE relTypeCode = ?", relType);
			sql.append("AND relId = ?", articleId);
			sql.append("AND memberId=?", memberId);
			sql.append("AND point=?;", likePoint);

			MysqlUtil.delete(sql);

		}

		else if (likeOrNot == 0) {

			int disLikePoint = -1;

			SecSql sql = new SecSql();
			sql.append("DELETE FROM `like`");
			sql.append("WHERE relTypeCode = ?", relType);
			sql.append("AND relId = ?", articleId);
			sql.append("AND memberId=?", memberId);
			sql.append("AND point=?;", disLikePoint);

			MysqlUtil.delete(sql);



		}
		
	}

	public void addReply(String rbody, int memberId, int articleId) {

		SecSql sql = new SecSql();
		sql.append("INSERT INTO reply");
		sql.append("SET relTypeCode = ? ", "article");
		sql.append(", relId=?", articleId);
		sql.append(", writerId=?", memberId);
		sql.append(", rbody=?",rbody);
		
		 MysqlUtil.insert(sql);
		
	}

	public List<Reply> getReplyByArticleId(int id) {
		List<Reply> replies = new ArrayList<>();

		SecSql sql = new SecSql();
		sql.append("SELECT r.*,m.nickname FROM reply as r");
		sql.append("JOIN `member` as m");
		sql.append("ON r.writerId = m.id");
		sql.append("WHERE relTypeCode = ?","article");
		sql.append("AND relId = ?",id);
		
		List<Map<String, Object>> replyMapList = MysqlUtil.selectRows(sql);

		for (Map<String, Object> replyMap : replyMapList) {
			replies.add(new Reply(replyMap));
		}
		return replies;
	}

	public void deleteReply(int id) {
		SecSql sql = new SecSql();
		sql.append("DELETE FROM reply");
		sql.append("WHERE id = ?",id);
		
		MysqlUtil.delete(sql);
	}

	public int getLikeAmount(int articleId, String relTypeCode, int i) {
		
		return 0;
	}
		
	}


