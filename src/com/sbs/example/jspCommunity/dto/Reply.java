package com.sbs.example.jspCommunity.dto;

import java.util.Map;

import lombok.Data;

@Data
public class Reply {
	int id;
	String regDate;
	String updateDate;
	String relTypeCode;
	String rbody;
	int relId;
	int memberId;
public Reply(Map<String, Object> replyMap) {
	this.id = (int) replyMap.get("id");
	this.regDate = (String) replyMap.get("regDate");
	this.updateDate = (String) replyMap.get("updateDate");
	this.rbody = (String) replyMap.get("rbody");
	this.relId = (int) replyMap.get("relId");
	this.relTypeCode = (String) replyMap.get("relTypeCode");
	this.memberId = (int) replyMap.get("writerId");
	
	
	}
	
}
