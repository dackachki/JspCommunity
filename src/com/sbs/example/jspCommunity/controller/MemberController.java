package com.sbs.example.jspCommunity.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sbs.example.Util.Util;
import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.dto.Attr;
import com.sbs.example.jspCommunity.dto.Member;
import com.sbs.example.jspCommunity.service.AttrService;
import com.sbs.example.jspCommunity.service.EmailService;
import com.sbs.example.jspCommunity.service.MemberService;

public class MemberController extends Controller {
	private MemberService memberService;
	private EmailService emailService;
	private AttrService attrService;

	public MemberController() {
		memberService = Container.memberService;
		emailService = Container.emailService;
		attrService = Container.attrService;

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
		String loginId = (String) req.getParameter("loginId");
		String loginPw = (String) req.getParameter("loginPwReal");
		String nickname = (String) req.getParameter("nickname");
		String email = (String) req.getParameter("email");
		String cellphoneNo = (String) req.getParameter("cellphoneNo");
		Member oldMember = memberService.getMemberByLoginId(loginId);

		if (oldMember != null) {
			return msgAndBack(req,"해당 로그인 아이디는 이미 사용중입니다.");
			
		}

		memberService.memberJoin(name, loginId, loginPw, nickname, email, cellphoneNo);
		emailService.send(email, nickname+"님 [BlackCowEdition]가입이 완료되었습니다.", nickname+"님의 가입을 축하합니다.");

		return msgAndReplace(req, "회원 가입이 완료되었습니다.", "login");
	}

	public String login(HttpServletRequest req, HttpServletResponse resp) {
		return "usr/member/doLogin";

	}

	public String doLogin(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();

		String loginId = req.getParameter("loginId");
		String loginPw = req.getParameter("loginPwReal");

		Member member = memberService.getMemberByLoginId(loginId);
		if (member == null) {
			return msgAndBack(req, "존재하지 않는 아이디 입니다.");
				
		}

		if (member.getLoginPw().equals(loginPw) == false) {

			return msgAndBack(req, "비밀번호가 일치하지 않습니다.");

		}

		session.setAttribute("loginedMemberId", member.getId());
		session.setAttribute("loginedMemberNick", member.getNickname());

		Attr attr = attrService.get("member__" + member.getId() + "__extra__isUsingTempPassword");

		// isValue = boolean형 getter 임시 비번 여부 체크
		if (attr != null) {
			if (attr.isValue()) {
				session.setAttribute("isUsingTempPw", true);
				String[] dates = attr.getExpireDate().split(" ");
				session.setAttribute("expireDate", dates[0]);
			}
			else {
				session.setAttribute("isUsingTempPw", false);
			}
		}
		
		if(Util.isEmpty(req.getParameter("afterLoginURL")) == false) {
			String replaceUrl = req.getParameter("afterLoginURL");
			return msgAndReplace(req, member.getNickname() + "님 환영합니다.",replaceUrl);
			
		}
		else {
		return msgAndReplace(req, member.getNickname() + "님 환영합니다.", "../../usr/home/main");
		}
	}

	public String logout(HttpServletRequest req, HttpServletResponse resp) {
		return "usr/member/doLogout";
	}

	public String dologout(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();

		if (session.getAttribute("loginedMemberId") == null) {
			return msgAndBack(req, "이미 로그아웃 상태입니다.");
		}

		session.removeAttribute("loginedMemberId");
		session.removeAttribute("loginedMemberNick");
		session.removeAttribute("isUsingTempPw");

		req.setAttribute("alertMsg", "로그아웃 되었습니다.");
		req.setAttribute("replaceUrl", "../home/main");
		return msgAndReplace(req, "로그아웃 되었습니다.", "../home/main");
	}

	public String getLoginIdDup(HttpServletRequest req, HttpServletResponse resp) {
		String loginId = req.getParameter("loginId");

		Member member = memberService.getMemberByLoginId(loginId);

		String data = "";

		if (member != null) {
			data = "NO";
		} else if (loginId.trim().length() == 0) {
			data = "NO";
		} else {
			data = "YES";
		}

		req.setAttribute("data", data);
		return "common/pure";
	}

	public String findLoginId(HttpServletRequest req, HttpServletResponse resp) {
		return "usr/member/doFindLoginId";
	}

	public String doFindLoginId(HttpServletRequest req, HttpServletResponse resp) {

		String name = req.getParameter("name");
		String email = req.getParameter("email");

		Member member = memberService.getMemberByNameAndEmail(name, email);
		if (member == null) {
			return msgAndBack(req, "일치하는 회원 정보가 존재하지 않습니다.");
		}

		return msgAndReplace(req, "회원님의 아이디는" + member.getLoginId() + "입니다.", "login");

	}

	public String findLoginPw(HttpServletRequest req, HttpServletResponse resp) {
		return "usr/member/doFindLoginPw";
	}

	public String doFindLoginPw(HttpServletRequest req, HttpServletResponse resp) {
		SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.DATE, 90);
		// 기한 날짜 변경 불가하게
		final String expireDate = dformat.format(cal.getTime());

		String name = req.getParameter("name");
		String loginId = req.getParameter("loginId");

		Member member = memberService.getMemberByNameAndLoginId(loginId, name);

		if (member == null) {
			return msgAndBack(req, "일치하는 회원 정보가 존재하지 않습니다.");
		}

		int pwChangedMemberId = member.getId();

		attrService.setValue("member__" + pwChangedMemberId + "__extra__isUsingTempPassword", "1", expireDate);
		// member__64__extra_isUsingTempPassword = name
		// value = "1"
		// expireDate = 오늘 기준일 +90

		String tempPw = emailService.sendTempPw(member);
		String loginPw = Util.sha256(tempPw);
		memberService.modifyPw(loginPw, member.getId());

		return msgAndReplace(req, "가입시 입력한 이메일 주소로 임시 비밀번호를 발송하였습니다.", "login");

	}

	public String MModify(HttpServletRequest req, HttpServletResponse resp) {

		HttpSession session = req.getSession();
		Member member = (Member) session.getAttribute("loginedMember");
		req.setAttribute("member", member);
		return "usr/member/doMModify";

	}

	public String doMModify(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();

		String ChangedPw = req.getParameter("loginPwReal");
		String nickname = req.getParameter("nickname");
		String email = req.getParameter("email");
		String cellphoneNo = req.getParameter("cellphoneNo");
		int id = (int) session.getAttribute("loginedMemberId");

		boolean isUsingTempPw = (boolean) session.getAttribute("isUsingTempPw");

		memberService.memberModify(id, ChangedPw, nickname, email, cellphoneNo, isUsingTempPw);

		session.removeAttribute("isUsingTempPw");
		
		return msgAndReplace(req,"회원 정보가 수정되었습니다.","../home/main");

	}

}
