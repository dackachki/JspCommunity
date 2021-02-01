package com.sbs.example.jspCommunity.controller;

import javax.servlet.http.HttpServletRequest;

public class Controller {

	protected String msgAndBack(HttpServletRequest req, String msg) {
		req.setAttribute("alertMsg", msg);
		
		return "common/";
		
		
		
	}
	
}
