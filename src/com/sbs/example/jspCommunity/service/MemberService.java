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
			if(member.loginId.equals(loginId)) {
				return false;
			}
		}
		return true;
	}

	public void memberJoin(String name, String loginId, String loginPw, String nickname, String email, String cellphoneNo) {
		 memberDao.memberJoin(name,loginId,loginPw,nickname,email,cellphoneNo);
		
	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);

	}

	public Member getMemberById(int loginedMemberId) {
		return memberDao.getMemberById(loginedMemberId);
	}

	public Member getMemberByNameAndEmail(String name, String email) {
		return memberDao.getMemberByNameAndEmail(name,email);
	}

	public Member getMemberByNameAndLoginId(String loginId, String name) {
		return memberDao.getMemberByNameAndLoginId(loginId,name);
	}

	public void modifyPw(String loginPw, int id) {
		memberDao.modifyPw(loginPw,id);
		
	}

}
