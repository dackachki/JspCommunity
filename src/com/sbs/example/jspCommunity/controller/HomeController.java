package com.sbs.example.jspCommunity.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController {

	public String main(HttpServletRequest req, HttpServletResponse resp) {

		return "usr/home/main";

	}
}
