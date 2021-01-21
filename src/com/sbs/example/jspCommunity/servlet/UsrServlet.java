package com.sbs.example.jspCommunity.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.controller.ArticleController;
import com.sbs.example.jspCommunity.controller.HomeController;
import com.sbs.example.jspCommunity.controller.MemberController;
import com.sbs.example.mysqlutil.MysqlUtil;

@WebServlet("/usr/*")
public class UsrServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		String requestUri = req.getRequestURI();
		String[] requestUriBits = requestUri.split("/");

		if (requestUriBits.length < 5) {
			resp.getWriter().append("올바른 요청이 아닙니다.");
			return;
		}

		String controllerName = requestUriBits[3];
		String actionMethodName = requestUriBits[4];

		MysqlUtil.setDBInfo("127.0.0.1", "sbsst", "sbs123414", "jspCommunity");

		String jspPath = null;

		if (controllerName.equals("member")) {
			MemberController memberController = Container.memberController;

			if (actionMethodName.equals("join")) {
				jspPath = memberController.join(req, resp);
			} else if (actionMethodName.equals("doJoin")) {
				jspPath = memberController.doJoin(req, resp);
			}
			 else if (actionMethodName.equals("login")) {
					jspPath = memberController.login(req, resp);
				}
			 else if (actionMethodName.equals("doLogin")) {
					jspPath = memberController.doLogin(req, resp);
				}
			 else if (actionMethodName.equals("logout")) {
					jspPath = memberController.logout(req, resp);
				}
			 else if (actionMethodName.equals("doLogout")) {
					jspPath = memberController.dologout(req, resp);
				}
			 else if (actionMethodName.equals("getLoginIdDup")) {
					jspPath = memberController.getLoginIdDup(req, resp);
				}

		} else if (controllerName.equals("article")) {
			ArticleController articleController = Container.articleController;

			if (actionMethodName.equals("list")) {
				jspPath = articleController.showList(req, resp);

			} else if (actionMethodName.equals("add")) {
				jspPath = articleController.add(req, resp);
			} else if (actionMethodName.equals("doAdd")) {

				jspPath = articleController.doAdd(req, resp);
			} else if (actionMethodName.equals("detail")) {
				jspPath = articleController.detail(req, resp);
			} else if (actionMethodName.equals("modify")) {
				jspPath = articleController.modify(req, resp);
			} else if (actionMethodName.equals("doModify")) {
				jspPath = articleController.doModify(req, resp);
			} else if (actionMethodName.equals("delete")) {
				jspPath = articleController.delete(req, resp);
			}
			
		}
		 else if (controllerName.equals("home")) {
				HomeController homeController = Container.homeController;
				 if (actionMethodName.equals("main")) {
					jspPath = homeController.main(req, resp);
				}	
		 }

		MysqlUtil.closeConnection();

		RequestDispatcher rd = req.getRequestDispatcher("/jsp/" + jspPath + ".jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
