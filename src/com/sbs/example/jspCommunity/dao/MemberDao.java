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
		
		MysqlUtil.update(new SecSql().append("insert into `member` set regDate =now(), updateDate =now(), name = ?, loginId = ?,loginPw = ?, nickname = ?, email = ?;",name,loginId,loginPw,nickname,email));

		
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
