package com.sbs.example.jspCommunity.servlet.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController {

	public String main(HttpServletRequest req, HttpServletResponse resp) {

		return "usr/home/main";

	}
}
