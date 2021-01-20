package com.sbs.example.jspCommunity.service;

import java.util.List;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.dao.ArticleDao;
import com.sbs.example.jspCommunity.dao.MemberDao;
import com.sbs.example.jspCommunity.dto.Member;

public class MemberService {

	private MemberDao memberDao;
	
	public MemberService() {
		memberDao = Container.memberDao;
	}

	public List<Member> getForPrintMembers() {
		return memberDao.getForPrintMembers();
	}

	public boolean loginIdCheck(String loginId) {
		List<Member> members = getForPrintMembers();
		for(Member member: members) {
			if(member.loginId == loginId) {
				return false;
			}
		}
		return true;
	}

	public void memberJoin(String name, String loginId, String loginPw, String nickname, String email) {
		memberDao.memberJoin(name,loginId,loginPw,nickname,email);
		
	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);

	}

}
