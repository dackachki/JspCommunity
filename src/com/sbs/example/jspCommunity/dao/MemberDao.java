package com.sbs.example.jspCommunity.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sbs.example.jspCommunity.dto.Member;
import com.sbs.example.mysqlutil.MysqlUtil;
import com.sbs.example.mysqlutil.SecSql;

public class MemberDao {

	public List<Member> getForPrintMembers() {
		List<Member> members = new ArrayList<>();

		SecSql sql = new SecSql();
		sql.append("SELECT M.*");
		sql.append("FROM `member` AS M");
		sql.append("ORDER BY M.id DESC");

		List<Map<String, Object>> mapList = MysqlUtil.selectRows(sql);

		for (Map<String, Object> map : mapList) {
			members.add(new Member(map));
		}

		return members;
	}

	public void memberJoin(String name, String loginId, String loginPw, String nickname, String email) {
		SecSql sql = new SecSql();
		sql.append("insert into `member`");
		sql.append("set name = ?",name);
		sql.append(",regDate =now()");
		sql.append(",updateDate =now()");
		sql.append(",nickname =?",nickname);
		sql.append(",loginId =?",loginId);
		sql.append(",loginPw =?",loginPw);
		sql.append(",email =?;",email);
		
	int id =MysqlUtil.insert(sql);

		
	}
	
	public Member getMemberByLoginId(String loginId) {
		SecSql sql = new SecSql();
		sql.append("SELECT * FROM `member`");
		sql.append("where loginId =?", loginId);

		Map<String, Object> memberMap = MysqlUtil.selectRow(sql);
		
		if(memberMap.isEmpty()) {
			return null;
		}
		Member member = new Member(memberMap);
		return member;

	}

}
