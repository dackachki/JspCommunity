package com.sbs.example.jspCommunity.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.controller.MemberController;
import com.sbs.example.mysqlutil.MysqlUtil;

@WebServlet("/adm/*")
public class AdmServlet extends DispatcherServlet {
	
	@Override
	protected String doAction(HttpServletRequest req, HttpServletResponse resp,String controllerName,String actionMethodName){

		String jspPath = null;

		if (controllerName.equals("member")) {
			MemberController memberController = Container.memberController;

			if (actionMethodName.equals("list")) {
				jspPath = memberController.showList(req, resp);
			}

		}
	return jspPath;
	}

	
}