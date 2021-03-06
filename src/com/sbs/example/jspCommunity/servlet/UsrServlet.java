package com.sbs.example.jspCommunity.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.controller.ArticleController;
import com.sbs.example.jspCommunity.controller.HomeController;
import com.sbs.example.jspCommunity.controller.MemberController;

@WebServlet("/usr/*")
public class UsrServlet extends DispatcherServlet {
	@Override
	protected String doAction(HttpServletRequest req, HttpServletResponse resp, String controllerName,
			String actionMethodName) {
		String jspPath = null;

		if (controllerName.equals("member")) {
			MemberController memberController = Container.memberController;

			if (actionMethodName.equals("join")) {
				jspPath = memberController.join(req, resp);

			} else if (actionMethodName.equals("doJoin")) {

				jspPath = memberController.doJoin(req, resp);
			} else if (actionMethodName.equals("login")) {

				jspPath = memberController.login(req, resp);
			} else if (actionMethodName.equals("doLogin")) {

				jspPath = memberController.doLogin(req, resp);
			} else if (actionMethodName.equals("logout")) {

				jspPath = memberController.logout(req, resp);
			} else if (actionMethodName.equals("doLogout")) {

				jspPath = memberController.dologout(req, resp);
			} else if (actionMethodName.equals("getLoginIdDup")) {

				jspPath = memberController.getLoginIdDup(req, resp);
			} else if (actionMethodName.equals("findLoginId")) {

				jspPath = memberController.findLoginId(req, resp);
			} else if (actionMethodName.equals("doFindLoginId")) {

				jspPath = memberController.doFindLoginId(req, resp);
			} else if (actionMethodName.equals("findLoginPw")) {

				jspPath = memberController.findLoginPw(req, resp);
			} else if (actionMethodName.equals("doFindLoginPw")) {

				jspPath = memberController.doFindLoginPw(req, resp);
			}

			else if (actionMethodName.equals("MModify")) {

				jspPath = memberController.MModify(req, resp);

			} else if (actionMethodName.equals("doMModify")) {

				jspPath = memberController.doMModify(req, resp);
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
			else if (actionMethodName.equals("addLike")) {
				jspPath = articleController.addLike(req, resp);
			}
			else if (actionMethodName.equals("addDislike")) {
				jspPath = articleController.addDislike(req, resp);
			}
			else if (actionMethodName.equals("removeLike")) {
				jspPath = articleController.removeLike(req, resp);
			}
			else if (actionMethodName.equals("removeDislike")) {
				jspPath = articleController.removeDislike(req, resp);
			}
			else if (actionMethodName.equals("addReply")) {
				jspPath = articleController.addreply(req, resp);
			}
			else if (actionMethodName.equals("deleteReply")) {
				jspPath = articleController.deleteReply(req, resp);
			}
			else if (actionMethodName.equals("addLikeR")) {
				jspPath = articleController.addLikeR(req, resp);
			}
			else if (actionMethodName.equals("addDislikeR")) {
				jspPath = articleController.addDislikeR(req, resp);
			}
			else if (actionMethodName.equals("removeLikeR")) {
				jspPath = articleController.removeLikeR(req, resp);
			}
			else if (actionMethodName.equals("removeDislikeR")) {
				jspPath = articleController.removeDislikeR(req, resp);
			}
			else if (actionMethodName.equals("articlehits")) {
				jspPath = articleController.articlehits(req, resp);
			}
		
		} else if (controllerName.equals("home")) {
			HomeController homeController = Container.homeController;
			if (actionMethodName.equals("main")) {
				jspPath = homeController.main(req, resp);
			}
		}

		return jspPath;
	}
}
