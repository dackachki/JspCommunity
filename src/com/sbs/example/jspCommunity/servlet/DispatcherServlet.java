package com.sbs.example.jspCommunity.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sbs.example.Util.Util;
import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.dto.Member;
import com.sbs.example.mysqlutil.MysqlUtil;

public abstract class DispatcherServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		run(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	public void run(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, Object> doBeforeActionRs = doBeforeAction(req, resp);

		if (doBeforeActionRs == null) {
			return;
		}

		String jspPath = doAction(req, resp, (String) doBeforeActionRs.get("controllerName"),
				(String) doBeforeActionRs.get("actionMethodName"));

		if (jspPath == null) {
			resp.getWriter().append(jspPath + "jsp 정보가 없습니다.");
			return;
		}

		doAfterAction(req, resp, jspPath);
	}

	private Map<String, Object> doBeforeAction(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		String requestUri = req.getRequestURI();
		String[] requestUriBits = requestUri.split("/");

		String profilesActive = System.getProperty("spring.profiles.active");

		boolean isProductionMode = false;

		if (profilesActive != null && profilesActive.equals("production")) {
			isProductionMode = true;
		}

		int requestUriBitsMinCount = 5;

		if (isProductionMode) {
			requestUriBitsMinCount = 4;
		}

		if (requestUriBits.length < requestUriBitsMinCount) {
			resp.getWriter().append("올바른 요청이 아닙니다.");
			return null;
		}

		if (isProductionMode) {
			MysqlUtil.setDBInfo("127.0.0.1", "sbsst", "sbs123414", "jspCommunityReal");
		} 
		else {
			MysqlUtil.setDBInfo("127.0.0.1", "sbsst", "sbs123414", "jspCommunity");
			Util.setDevMode(true);
			
		}

		String directoryName;
		String controllerName;
		String actionMethodName;

		if (isProductionMode) {
			directoryName = requestUriBits[1];
			controllerName = requestUriBits[2];
			actionMethodName = requestUriBits[3];
		} else {
			directoryName = requestUriBits[2];
			controllerName = requestUriBits[3];
			actionMethodName = requestUriBits[4];
		}

		String actionUrl = "/" + directoryName + "/" + controllerName + "/" + actionMethodName;
		boolean isLogined = false;
		int loginedMemberId = 0;
		Member loginedMember = null;

		HttpSession session = req.getSession();

		if (session.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
			loginedMember = Container.memberService.getMemberById(loginedMemberId);
		}

		req.setAttribute("isLogined", isLogined);
		req.setAttribute("loginedMemberId", loginedMemberId);
		req.setAttribute("loginedMember", loginedMember);

		String currentURL = req.getRequestURI();

		if (req.getQueryString() != null) {
			currentURL += "?" + req.getQueryString();

		}

		String encodedcurrentURL = Util.getUrlEncoded(currentURL);

		req.setAttribute("currentURL", currentURL);
		req.setAttribute("encodedcurrentURL", encodedcurrentURL);

		List<String> loginRequiredList = new ArrayList<>();
		loginRequiredList.add("/usr/member/doLogout");
		loginRequiredList.add("/usr/article/add");
		loginRequiredList.add("/usr/article/doWrite");
		loginRequiredList.add("/usr/article/modify");
		loginRequiredList.add("/usr/article/doModify");
		loginRequiredList.add("/usr/article/doDelete");
		loginRequiredList.add("/usr/article/MModify");
		loginRequiredList.add("/usr/article/doMModify");

		List<String> loginNotRequiredList = new ArrayList<>();
		loginNotRequiredList.add("/usr/member/doLogin");
		loginNotRequiredList.add("/usr/member/login");
		loginNotRequiredList.add("/usr/member/doJoin");
		loginNotRequiredList.add("/usr/member/join");
		loginNotRequiredList.add("/usr/member/doFindLoginPw");
		loginNotRequiredList.add("/usr/member/doFindLoginId");

		if (loginRequiredList.contains(actionUrl)) {

			if (session.getAttribute("loginedMemberId") == null) {
				req.setAttribute("alertMsg", "로그인 후 이용해주세요.");
				req.setAttribute("replaceUrl", "../member/login?afterLoginURL=" + encodedcurrentURL);
				RequestDispatcher rd = req.getRequestDispatcher("/jsp/common/redirect.jsp");
				rd.forward(req, resp);
			}
		}

		if (loginNotRequiredList.contains(actionUrl)) {
			if (session.getAttribute("loginedMemberId") != null) {
				req.setAttribute("alertMsg", "현재 로그인 중입니다.");
				req.setAttribute("replaceUrl", "../home/main");
				RequestDispatcher rd = req.getRequestDispatcher("/jsp/common/redirect.jsp");
				rd.forward(req, resp);
			}
		}

		Map<String, Object> rs = new HashMap<>();
		rs.put("controllerName", controllerName);
		rs.put("actionMethodName", actionMethodName);

		return rs;
	}

	protected abstract String doAction(HttpServletRequest req, HttpServletResponse resp, String controllerName,
			String actionMethodName);

	private void doAfterAction(HttpServletRequest req, HttpServletResponse resp, String jspPath)
			throws ServletException, IOException {
		MysqlUtil.closeConnection();

		RequestDispatcher rd = req.getRequestDispatcher("/jsp/" + jspPath + ".jsp");
		rd.forward(req, resp);
	}

}