package com.sbs.example.jspCommunity.service;

import com.sbs.example.Util.Util;
import com.sbs.example.jspCommunity.dto.Member;

public class EmailService {
	private String gmailId;
	private String gmailPw;
	private String from;
	private String fromName;

	public void init(String gmailId, String gmailPw, String from, String fromName) {
		this.gmailId = gmailId;
		this.gmailPw = gmailPw;
		this.from = from;
		this.fromName = fromName;
	}

	public int send(String to, String title, String body) {
		return Util.sendMail(gmailId, gmailPw, from, fromName, to, title, body);
	}

	public String sendTempPw(Member member) {
		
		String CommuTitle = "[BlackCow]";
		
		String title = CommuTitle+"임시 패스워드입니다.";
		int tempPwLengh=8;
		String changedPw = Util.getTempPassword(tempPwLengh);
		String body = "임시 패스워드 :" + changedPw;
		
		send(member.getEmail(),title,body);
		return changedPw;
	}
	
	
}