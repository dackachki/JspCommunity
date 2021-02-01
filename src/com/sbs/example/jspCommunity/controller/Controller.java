package com.sbs.example.jspCommunity.controller;

import javax.servlet.http.HttpServletRequest;

public class Controller {

	protected String msgAndBack(HttpServletRequest req, String msg) {
		req.setAttribute("alertMsg", msg);
		req.setAttribute("historyBack", true);
		return "common/redirect";
	}

	protected String msgAndReplace(HttpServletRequest req, String msg, String replaceUrl) {
		req.setAttribute("alertMsg", msg);
		req.setAttribute("replaceUrl", replaceUrl);
		return "common/redirect";
	}
	
	
	
}
