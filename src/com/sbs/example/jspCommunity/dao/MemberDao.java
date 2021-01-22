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

	public int memberJoin(String name, String loginId, String loginPw, String nickname, String email) {

		SecSql sql = new SecSql();
		sql.append("INSERT INTO `member`");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", loginId = ?", loginId);
		sql.append(", loginPw = ?", loginPw);
		sql.append(", nickname = ?", nickname);
		sql.append(", `name` = ?", name);
		sql.append(", email = ?", email);

		return MysqlUtil.insert(sql);
	}

	public Member getMemberByLoginId(String loginId) {
		SecSql sql = new SecSql();
		sql.append("SELECT * FROM `member`");
		sql.append("where loginId =?", loginId);

		Map<String, Object> memberMap = MysqlUtil.selectRow(sql);

		if (memberMap.isEmpty()) {
			return null;
		}
		Member member = new Member(memberMap);
		return member;

	}

	public Member getMemberById(int loginedMemberId) {
		SecSql sql = new SecSql();
		sql.append("SELECT * FROM `member`");
		sql.append("where id =?", loginedMemberId);

		Map<String, Object> memberMap = MysqlUtil.selectRow(sql);

		if (memberMap.isEmpty()) {
			return null;
		}
		Member member = new Member(memberMap);
		return member;
	}

}
