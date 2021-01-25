package com.sbs.example.jspCommunity.dto;

import java.util.Map;

import lombok.Data;
@Data
public class Article {
	private int id;
	private String regDate;
	private String updateDate;
	private String title;
	private String body;
	private int memberId;
	private int boardId;
	private int hitsCount;
	private String extra__writer;
	private String extra__nickname;
	private String extra__boardName;
	private String extra__boardCode;
	
	

	


	public Article(Map<String, Object> map) {
		this.id = (int) map.get("id");
		this.regDate = (String) map.get("regDate");
		this.updateDate = (String) map.get("updateDate");
		this.title = (String) map.get("title");
		this.body = (String) map.get("body");
		this.memberId = (int) map.get("memberId");
		this.boardId = (int) map.get("boardId");
		this.hitsCount = (int) map.get("hitsCount");
		

		if (map.containsKey("extra__writer")) {
			this.extra__writer = (String) map.get("extra__writer");
		}

		if (map.containsKey("extra__boardName")) {
			this.extra__boardName = (String) map.get("extra__boardName");
		}

		if (map.containsKey("extra__boardCode")) {
			this.extra__boardCode = (String) map.get("extra__boardCode");
		}
		if (map.containsKey("extra__nickname")) {
			this.extra__nickname = (String) map.get("extra__nickname");
		}
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", regDate=" + regDate + ", updateDate=" + updateDate + ", title=" + title + ", body=" + body + ", memberId=" + memberId + ", boardId=" + boardId + ", hitsCount=" + hitsCount + ", extra__writer=" + extra__writer
				+ ", extra__boardName=" + extra__boardName + ", extra__boardCode=" + extra__boardCode + "]";
	}

}