package com.sbs.example.jspCommunity.dto;

import java.util.Map;

import lombok.Data;
@Data
public class Member {
	private int id;
	private String regDate;
	private String updateDate;
	public String loginId;
	private String loginPw;
	private String name;
	private String nickname;
	private String email;
	private String cellphoneNo;
	private int authLevel;
	
	


	public Member(Map<String, Object> map) {
		this.id = (int) map.get("id");
		this.regDate = (String) map.get("regDate");
		this.updateDate = (String) map.get("updateDate");
		this.loginId = (String) map.get("loginId");
		this.loginPw = (String) map.get("loginPw");
		this.name = (String) map.get("name");
		this.nickname = (String) map.get("nickname");
		this.email = (String) map.get("email");
		this.cellphoneNo = String.valueOf(map.get("cellphoneNo"));
		this.authLevel = (int) map.get("adminLevel");
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", regDate=" + regDate + ", updateDate=" + updateDate + ", loginId=" + loginId + ", loginPw=" + loginPw + ", name=" + name + ", nickname=" + nickname + ", email=" + email + ", cellphoneNo=" + cellphoneNo
				+ ", authLevel=" + authLevel + "]";
	}

}
