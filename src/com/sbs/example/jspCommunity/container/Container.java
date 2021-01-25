package com.sbs.example.jspCommunity.container;

import com.sbs.example.jspCommunity.controller.ArticleController;
import com.sbs.example.jspCommunity.controller.HomeController;
import com.sbs.example.jspCommunity.controller.MemberController;
import com.sbs.example.jspCommunity.dao.ArticleDao;
import com.sbs.example.jspCommunity.dao.MemberDao;
import com.sbs.example.jspCommunity.service.ArticleService;
import com.sbs.example.jspCommunity.service.EmailService;
import com.sbs.example.jspCommunity.service.MemberService;

public class Container {
	public static ArticleController articleController;
	public static ArticleService articleService;
	public static ArticleDao articleDao;
	
	public static MemberController memberController;
	public static MemberService memberService;
	public static MemberDao memberDao;
	public static HomeController homeController;
	public static EmailService emailService;
	
	
	static {
		
		emailService = new EmailService();
		
		articleDao = new ArticleDao();
		articleService = new ArticleService();
		articleController =new ArticleController();
		
		memberDao = new MemberDao();
		memberService = new MemberService();
		memberController = new MemberController();
	
		homeController = new HomeController();
	}



}
