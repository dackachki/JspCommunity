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

	public int memberJoin(String name, String loginId, String loginPw, String nickname, String email, String cellphoneNo) {

		SecSql sql = new SecSql();
		sql.append("INSERT INTO `member`");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", loginId = ?", loginId);
		sql.append(", loginPw = ?", loginPw);
		sql.append(", nickname = ?", nickname);
		sql.append(", `name` = ?", name);
		sql.append(", email = ?", email);
		sql.append(",cellphoneNo = ? ",cellphoneNo);

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

	public Member getMemberByNameAndEmail(String name, String email) {
		SecSql sql = new SecSql();
		sql.append("SELECT * FROM `member`");
		sql.append("where name =?", name);
		sql.append("AND email =? ", email);
		sql.append("ORDER BY ID DESC LIMIT 1");

		Map<String, Object> memberMap = MysqlUtil.selectRow(sql);

		if (memberMap.isEmpty()) {
			return null;
		}
		Member member = new Member(memberMap);
		return member;
	}

	public Member getMemberByNameAndLoginId(String loginId, String name) {
		SecSql sql = new SecSql();
		sql.append("SELECT * FROM `member`");
		sql.append("where name =?", name);
		sql.append("AND loginId =? ", loginId);
		sql.append("ORDER BY ID DESC LIMIT 1");

		Map<String, Object> memberMap = MysqlUtil.selectRow(sql);

		if (memberMap.isEmpty()) {
			return null;
		}
		Member member = new Member(memberMap);
		return member;
	}

	public int modify(Map<String, Object> args) {
		SecSql sql = new SecSql();
		sql.append("UPDATE article");
		sql.append("SET updateDate = NOW()");

		boolean needToUpdate = false;

		if (args.get("title") != null) {
			needToUpdate = true;
			sql.append(", title = ?", args.get("title"));
		}

		if (args.get("body") != null) {
			needToUpdate = true;
			sql.append(", `body` = ?", args.get("body"));
		}

		if (needToUpdate == false) {
			return 0;
		}

		sql.append("WHERE id = ?", args.get("id"));

		return MysqlUtil.update(sql);
	}

	public void modifyPw(String loginPw, int id) {
		SecSql sql = new SecSql();
		sql.append("UPDATE `member`");
		sql.append("SET loginPw = ?", loginPw);
		sql.append("WHERE id = ? ", id);
	
		MysqlUtil.update(sql);
	}
	}
	


