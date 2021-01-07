package com.sbs.example.jspCommunity.servlet.usr;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.mysqlutil.MysqlUtil;
import com.sbs.example.mysqlutil.SecSql;

@WebServlet("/usr/article/")
public class articleListByBoard extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		MysqlUtil.setDBInfo("127.0.0.1", "sbsst", "sbs123414", "jspCommunity");
		
		List<Map<String, Object>> infoMapList = MysqlUtil.selectRows(new SecSql().append("SELECT * FROM article where boardId = 1 ORDER BY id DESC"));
		System.out.println(infoMapList);
		MysqlUtil.closeConnection();
		
		req.setAttribute("infoMapList", infoMapList);
		
		req.getRequestDispatcher("/jsp/usr/article/infolist.jsp").forward(req, resp);
	}
}
