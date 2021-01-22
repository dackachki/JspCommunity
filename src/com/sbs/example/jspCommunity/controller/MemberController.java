package com.sbs.example.jspCommunity.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	
		
		String name = req.getParameter("name");
		String loginId = (String)req.getParameter("loginId");
		String loginPw = (String)req.getParameter("loginPw");
		String nickname = (String)req.getParameter("nickname");
		String email = (String)req.getParameter("email");
		Member oldMember = memberService.getMemberByLoginId(loginId);

		
		if (oldMember != null) {
			req.setAttribute("alertMsg", "해당 로그인 아이디는 이미 사용중입니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}
		
		memberService.memberJoin(name,loginId,loginPw,nickname,email);
		
		
		req.setAttribute("alertMsg", "회원 가입이 완료되었습니다.");
		req.setAttribute("replaceUrl", String.format("login"));
		return "common/redirect";
	}

	public String login(HttpServletRequest req, HttpServletResponse resp) {
		return "usr/member/doLogin";
		
	}

	public String doLogin(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		
		String loginId = req.getParameter("loginId");
		String loginPw = req.getParameter("loginPw");
		
		Member member = memberService.getMemberByLoginId(loginId);
		if(member == null) {
			req.setAttribute("alertMsg", "존재하지 않는 아이디 입니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}
		
		if(member.getLoginPw().equals(loginPw) == false) {

			req.setAttribute("alertMsg", "비밀번호가 일치하지 않습니다..");
			req.setAttribute("historyBack", true);
			return "common/redirect";
			
		}
		session.setAttribute("loginedMemberId", member.getId());
		session.setAttribute("loginedMemberNick", member.getNickname());
		
		req.setAttribute("alertMsg", member.getNickname()+"님 환영합니다.");
		req.setAttribute("replaceUrl","../../usr/home/main");
		return "common/redirect";
	
		
	}

	public String logout(HttpServletRequest req, HttpServletResponse resp) {
		return "usr/member/doLogout";
	}

	public String dologout(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();

		if (session.getAttribute("loginedMemberId") == null) {
			req.setAttribute("alertMsg", "이미 로그아웃 상태입니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		session.removeAttribute("loginedMemberId");
		session.removeAttribute("loginedMemberNick");

		req.setAttribute("alertMsg", "로그아웃 되었습니다.");
		req.setAttribute("replaceUrl", "../home/main");
		return "common/redirect";
	}
	public String getLoginIdDup(HttpServletRequest req, HttpServletResponse resp) {
		String loginId = req.getParameter("loginId");
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		String data = "";
		
		if ( member != null ) {
			data = "NO";
		}
		else if (loginId.trim().length() == 0) {
			data = "NO";
		}
		else {
			data = "YES";
		}
		
		req.setAttribute("data", data);
		return "common/pure";
	}
	

}
