package com.sbs.example.jspCommunity.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.dto.Board;
import com.sbs.example.jspCommunity.dto.Member;
import com.sbs.example.jspCommunity.service.MemberService;

public class MemberController {
	private MemberService memberService;
	
	public MemberController() {
		memberService = Container.memberService;
	}
	
	public String showList(HttpServletRequest req, HttpServletResponse resp) {
		List<Member> members = memberService.getForPrintMembers();
		
		req.setAttribute("members", members);
		
		return "adm/member/list";
	}

	public String join(HttpServletRequest req, HttpServletResponse resp) {
		
		
		
		return "usr/member/doJoin";
		
	}

	public String doJoin(HttpServletRequest req, HttpServletResponse resp) {
	
		
		String name = req.getParameter("membername");
		String loginId = (String)req.getParameter("loginId");
		if(memberService.loginIdCheck(loginId) == false) {
			req.setAttribute("alertMsg","이미 존재하는 아이디 입니다.");
		}
		String loginPw = (String)req.getParameter("loginPw");
		String nickname = (String)req.getParameter("nickname");
		String email = (String)req.getParameter("email");
		System.out.printf("%s/%s/%s/%s/%s",name,loginId,loginPw,nickname,email);
		
		memberService.memberJoin(name,loginId,loginPw,nickname,email);
		
		
		req.setAttribute("alertMsg", "가입이 완료되었습니다.");
		req.setAttribute("replaceUrl", String.format("../article/list"));
		return "common/redirect";
	}

}
